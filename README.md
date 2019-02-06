# UNIXshell

> This project is a simulation of a basic command line environment that supports concurrency for interacting with a UNIX file system. 
> Each command executes in a separate thread, thus allowing the program to run concurrently. Commands can also be run as background processes.

## **Supported Commands**
### 1. Working Directory Commands:
  - pwd : Pipes the working directory to the output message queue
  - ls : Pipes the contents of the working directory to the output message queue
  - cd : Change to another directory relative to the current one
  - exit : Quit the command line
### 2. Basic Text Manipulation:
  - cat : Output the entirety of one or more files to the output message queue
  - grep : Read lines from piped input, and only output lines with a given search string
  - wc : Read lines from piped input and output the number of lines, words and chars, seperated by spaces
  - uniq : Filters out duplicate lines from the input
### 3. Redirection and Piping:
  - | : Pipes output from the command before the | symbol as input for the trailing command
  - \> : Reads piped input, and writes it to the file specified after the > symbol
### 4. Concurrency:
  - & : Adding this to the end of a command causes it to execute as a background process, printing output as soon as the process terminates
  - repl_jobs : Prints a list of background processes that are currently running 
