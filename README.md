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

