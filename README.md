# SJF-Process

Your computer performs multiple processes, ranging from mundane stuff like disk checkup to
more important matters such as installing Eclipse. The process scheduler is an important
component of the operating system that is responsible for deciding which process will run next.
In this assignment, we are going to develop a simple process scheduler to schedule processes that
are ready to be run. This scheduler consists of only one processor. This means that only one
process can be executed at a time. Our scheduler will run on a heap-based priority queue, and
will operate conforming to the Shortest Job First (SJF), or shortest job next scheduling policy.
In particular, this scheduling policy selects the ready process with the smallest execution time
(aka burst time) to execute next. [Burst Time: Time required by a process to be executed in
the Central Processing Unit (CPU)]. This programming assignment represents an interesting
use case of the priority queue abstract data type implemented using a min-heap.
