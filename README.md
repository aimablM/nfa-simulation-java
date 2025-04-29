# NFA-Simulation-Java
🧠 Diagram Info:
The NFA has:

States: q1, q2, q3

Start State: q1

Accepting State: q2

Transitions:

q1 → q2 on 0 or 1

q1 → q3 on ε (epsilon)

q3 → q1 on ε (epsilon)

q3 → q2 on 0

(There is a cycle between q1 and q3 through ε-transitions.)

🧠 Now matching to numbers:
Let’s label:

q1 → 1

q2 → 2

q3 → 3

✅ nfa.txt CONTENT:
Copy
Edit
3
2
1 0 2
1 1 2
1 -1 3
3 -1 1
3 0 2

Line	Meaning
3	Number of states (1, 2, 3)
2	Accepting state is 2
1 0 2	From state 1 on input 0, go to state 2
1 1 2	From state 1 on input 1, go to state 2
1 -1 3	From state 1 via ε-move, go to 3
3 -1 1	From state 3 via ε-move, go to 1
3 0 2	From state 3 on input 0, go to 2
🗂 Instructions
✅ Create a plain text file called nfa.txt.
✅ Copy-paste the above 6 lines exactly.
✅ Save it in the same folder as your NFASimulator.java.

🧪 Test cases from assignment:

Input string	Expected Output
0	ACCEPT
01	ACCEPT
110	ACCEPT
0100	ACCEPT
100	REJECT
(You will see why when we explain the ε-cycles.)

🚀 QUICK NEXT STEPS CHECKLIST:
 Save the nfa.txt above.

 Compile and run your program.

 Enter nfa.txt when asked.

 Test with 0, 01, 110, 0100, and 100.

