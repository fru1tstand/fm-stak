# Stak
The task organizer.

This is a re-launch of an old project that was once created in PHP.

## Features
+ Organize multiple projects
+ Track a todo list with priority
+ Create status reports with both time- and project- centric formatting
+ Easily integrate with other services (Github commits, Github issue tracker, proprietary software)
+ Support small-window functionality for always-on-top todo list window viewing

# Git Hooks
## pre-push
Save this as a file in `.git/hooks` called `pre-push`. This will run unit tests before pushing.
```
#!/bin/bash
CMD="./gradlew clean test"

# Check if we actually have commits to push
commits=`git log @{u}..`
if [ -z "$commits" ]; then
 exit 0
fi

$CMD
RESULT=$?
if [ $RESULT -ne 0 ]; then
 echo "failed $CMD"
 exit 1
fi

exit 0
```
