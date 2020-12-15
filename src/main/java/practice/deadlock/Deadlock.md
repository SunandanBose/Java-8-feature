# Deadlock


## How does deadlock occur?

    synchronized keyword is used to make the class or method thread-safe which means only one thread can 
    have lock of synchronized method and use it, other threads have to wait till the lock releases and anyone 
    of them acquire that lock. 
    It is important to use if our program is running in multi-threaded environment where two or more threads 
    execute simultaneously. But sometimes it also causes a problem which is called Deadlock.

## How to capture Thread Dump?
- get process id(PID) by running command ```jps```
- run the jcmd command to get the thread dump details. ```jcmd <PID> Thread.print >> <Output>```