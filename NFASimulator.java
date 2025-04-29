import java.io.*;
import java.util.*;

public class NFASimulator {
    private static final int EPSILON = -1;

    private int numStates;
    private Set<Integer> acceptingStates = new HashSet<>();
    private Map<Integer, Map<Integer, List<Integer>>> transitions = new HashMap<>();

    public void loadNFAFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        // Read number of states
        numStates = Integer.parseInt(reader.readLine().trim());

        // Read accepting states
        String[] accepting = reader.readLine().trim().split("\\s+");
        for (String state : accepting) {
            acceptingStates.add(Integer.parseInt(state));
        }

        // Read transitions
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length != 3) continue;
            int from = Integer.parseInt(parts[0]);
            int symbol = Integer.parseInt(parts[1]);
            int to = Integer.parseInt(parts[2]);

            transitions
                .computeIfAbsent(from, k -> new HashMap<>())
                .computeIfAbsent(symbol, k -> new ArrayList<>())
                .add(to);
        }

        reader.close();
    }

    // Debug print for loaded transitions
    public void printNFA() {
        System.out.println("States: " + numStates);
        System.out.println("Accepting states: " + acceptingStates);
        System.out.println("Transitions:");
        for (int from : transitions.keySet()) {
            for (int symbol : transitions.get(from).keySet()) {
                for (int to : transitions.get(from).get(symbol)) {
                    String label = (symbol == EPSILON) ? "ε" : String.valueOf(symbol);
                    System.out.printf("  %d -- %s --> %d%n", from, label, to);
                }
            }
        }
    }

    // Compute the epsilon closure of a set of states
private Set<Integer> epsilonClosure(Set<Integer> states) {
    Set<Integer> closure = new HashSet<>(states);
    Stack<Integer> stack = new Stack<>();
    stack.addAll(states);

    while (!stack.isEmpty()) {
        int state = stack.pop();
        List<Integer> epsilonMoves = transitions
            .getOrDefault(state, new HashMap<>())
            .getOrDefault(EPSILON, new ArrayList<>());

        for (int nextState : epsilonMoves) {
            if (!closure.contains(nextState)) {
                closure.add(nextState);
                stack.push(nextState);
            }
        }
    }

    return closure;
}

// Simulate the NFA on the given input string
public boolean simulate(String input) {
    Set<Integer> currentStates = new HashSet<>();
    currentStates.add(1); // start state
    currentStates = epsilonClosure(currentStates);

    for (int i = 0; i < input.length(); i++) {
        int symbol = Character.getNumericValue(input.charAt(i));
        Set<Integer> nextStates = new HashSet<>();

        for (int state : currentStates) {
            List<Integer> moves = transitions
                .getOrDefault(state, new HashMap<>())
                .getOrDefault(symbol, new ArrayList<>());

            nextStates.addAll(moves);
        }

        // After moving with symbol, apply epsilon closure again
        currentStates = epsilonClosure(nextStates);

        if (currentStates.isEmpty()) {
            break; // No possible moves, can stop early
        }
    }

    // After consuming input, check if any current state is accepting
    for (int state : currentStates) {
        if (acceptingStates.contains(state)) {
            return true; // ACCEPT
        }
    }

    return false; // REJECT
}

    public static void main(String[] args) {
        NFASimulator simulator = new NFASimulator();
        Scanner scanner = new Scanner(System.in);
    
        try {
            System.out.print("Enter the filename of the NFA (e.g., nfa.txt): ");
            String filename = scanner.nextLine().trim();
            simulator.loadNFAFromFile(filename);
    
            simulator.printNFA(); // Optional: can be commented out
    
            System.out.print("Enter an input string of 0s and 1s: ");
            String input = scanner.nextLine().trim();
    
            boolean accepted = simulator.simulate(input);
            System.out.println(accepted ? "ACCEPT" : "REJECT");
    
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    
        scanner.close();
    }
    


}
