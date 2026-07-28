## I used to host a server in my home computer.

## Problem:
**ISP now blocks ports, so it isn't possible to host the server anymore**.
That means that it's hard to host a personal server without making a request.
Having incoming traffic in required ports **may not be free**.

So this is the source code...

## What was the server for:
It was a sever that was replying to get\_version\_code?[appname] requests.
So my apps was checking for updates via this server.
I was using [https://www.duckdns.org](url) for DNS,
my **manjaro linux** personal computer as a server
and simple **JAVA**  and **BASH** for the sever.

### The interesting thing!
The interesting thing is that this server shows a Java Mug animation in the left side of the logs while running.
This was working for my linux terminal (xfce4-terminal) with size 237x63 but it's likely that it won't work in a smaller one.

Also, now because of ISP, all my apps are broken because they were using this server.
Now I have to fix them all...
