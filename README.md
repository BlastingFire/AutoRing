# AutoRing
Plays automatically a mp3 file like a school ring or a notification at every hour &amp; minute specified.

Set you own time:
  Check in the main loop, you can see a lot of if/else if blocks. Just edit the value what must equals to "h" (=hours) and "m"
  (=minutes). You can add or delete some if & else if blocks or add some, just remember to add a call to "ring()" method.
  
By default, the ring file is located in your AppData/Roaming/sonnerie/ring.mp3, and if not, is automatically downloaded from the github repository, and updated if the file changes. You can delete the little try/catch at the top of the main method to disable the download from here, and add your proper ring file in the directory. Do not delete the useless statement "new JFXPanel();", used to make the mp3 player working.
