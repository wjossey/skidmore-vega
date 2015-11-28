VEGA

VEGA is both a program for visualizing algorithms, as well as a library of Graph classes that can aid the programmer in the development of their own algorithms.

Library

The VEGA library is currently under development, but so far has a large set of base classes to work with. The code utilizes the Java Generics framework which allows for compile-time type checking, so as to prevent class cast errors. This is not a requirement for the developer, but is highly recommended.

The code consists of a series of base classes that can be used to construct more complicated structures. All forms of Graphs, whether they be Trees or Networks, extend the base Abstract Graph class, which utilizes the Graph interface also included in the code.

It should be noted that almost every class included in VEGA has a corresponding interface that allows for custom development of classes to meet specific requirements and expectations.

VEGA Application

The VEGA Application is a tool that is meant to help users see how an algorithm or data-structure works by visually representing it using Graphviz. The reasoning behind the development of the application is that it is far easier to understand how something works when one moves beyond abstract mathematical notation and is able to see working demos.

VEGA has been developed such that it is not able to just represent a handful of algorithms and data-structures, but rather any algorithm or data-structure that adheres to some basic conventions. By implementing some basic methods, a user is able to take existing code of an algorithm or data-structure and output the necessary information to VEGA for representation.

Current Algorithms and Data Structures
We currently have implemented a handful of algorithms and data structures, and more are on the way. As of July 10, 2008, we have the following:

Fibonacci Heaps
Red Black Trees
Dijkstra's Algorithm
2-OPT (TSP Algorithm)
Nearest Neighbor Algorithm
Heap sort
Binary Search Tree
Prim's Algorithm
We anticipate having Thin and Thick heaps implemented by the end of summer 2008.
