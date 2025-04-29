# NFA-Simulation-Java
# COSC 417 Assignment - Programming Task 2
## NFA (Nondeterministic Finite Automaton) Simulator

---

## 🛠 Project Overview

This project simulates the operation of a nondeterministic finite automaton (NFA) with ε-transitions on a binary input string (`0`s and `1`s).

The NFA is:
- Read from an external file.
- Traversed using depth-first search with epsilon closure handling.
- Capable of handling cycles formed purely by ε-transitions.

The simulator outputs `ACCEPT` if the input string can lead to an accepting state, and `REJECT` otherwise.

---

## 📚 How It Works

1. **Load the NFA**:  
   The NFA definition is read from a text file structured as:
   - **Line 1**: Number of states.
   - **Line 2**: List of accepting states.
   - **Subsequent lines**: Transitions in the format `(startState, inputSymbol, endState)`.
     - `-1` denotes an ε-transition (no input consumed).

2. **Epsilon Closure Expansion**:  
   Before and after each input symbol transition, the epsilon closure is computed to account for ε-moves.

3. **Simulation**:  
   The program simulates all possible paths using:
   - Normal transitions (`0`, `1`).
   - Epsilon transitions (`ε`).

4. **Acceptance Check**:  
   If any computation path leads to an accepting state after processing the entire input, the machine accepts the string.

---

## 📂 NFA Input File Example (`nfa.txt`)

3 2 1 0 2 1 1 2 1 -1 3 3 -1 1 3 0 2

**Explanation**:
- **States**: 1, 2, 3
- **Accepting State**: 2
- **Transitions**:
  - From 1, input `0` → 2
  - From 1, input `1` → 2
  - From 1, ε-move → 3
  - From 3, ε-move → 1
  - From 3, input `0` → 2

---

## 🚀 How to Run

1. Save the provided Java file `NFASimulator.java`.
2. Create the `nfa.txt` file in the same directory with the structure shown above.
3. Compile the program:
   ```bash
   javac NFASimulator.java
   java NFASimulator


Test Cases
Test the simulator on the following inputs:


Input String	Expected Output
0	            ACCEPT
01	            ACCEPT
110	            ACCEPT
0100	        ACCEPT
100	            REJECT
Remember to capture screenshots for each test run.

🛠 Data Structures Used

Data Structure	Purpose

Map<Integer, Map<Integer, List<Integer>>> transitions	Store the NFA transitions, including ε-transitions.
Set<Integer> acceptingStates	Store accepting (final) states.
Set<Integer> currentStates	Track the current active set of states during simulation.
⚙️ Special Handling of Epsilon Cycles
To prevent infinite loops caused by cycles of ε-transitions:

The epsilon closure uses a stack to explore reachable states.

A visited set ensures each state is processed only once per closure computation.







