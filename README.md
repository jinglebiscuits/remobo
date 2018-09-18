# remobo
Submission for TokBox 2018 hackathon

We thought it'd be cool to make a first person robot you could drive from anywhere in the world, so here it is.

An arduino on the robot is connected to a bluetooth receiver and a motor controller, and drives the motors according to instructions sent over the bluetooth receiver. These instructions are sent from an android phone also mounted to the robot, which in turn receives its commands from the OpenTok Signaling API. The phone also sends a video stream back over OpenTok to the person controlling the robot, around the corner or around the globe!
