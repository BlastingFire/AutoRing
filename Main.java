package sonnerie;

import javafx.embed.swing.JFXPanel;
        import javafx.scene.media.Media;
        import javafx.scene.media.MediaPlayer;

        import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
        import java.io.IOException;
import java.net.URL;
        import java.nio.channels.Channels;
        import java.nio.channels.ReadableByteChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;

public class Main {
    public static final String RING_LOCATION = System.getenv("appdata")+"/autosonnerie/ring.mp3";
    public static final String RING_LOCATION_BIS = System.getenv("appdata")+"/autosonnerie/ringt.mp3";
    public static final String RING_LOCATION_INTERNET = "https://raw.githubusercontent.com/BlastingFire/AutoRing/master/ring.mp3";
    public static void main(String[] args) {


            File f = new File(RING_LOCATION);
            boolean CHECK_FOR_UPDATES = true;
            if(CHECK_FOR_UPDATES) {
                if (!f.exists()) {
                    replace();
                } else {

                    try {
                        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
                        String checksumf = getFileChecksum(md5Digest, f);
                        String checksumd = downloadAndGetMd5();
                        if (!checksumd.equalsIgnoreCase(checksumf)) {
                            f.delete();
                            replace();
                        }
                    } catch (NoSuchAlgorithmException | IOException e) {
                        e.printStackTrace();
                    }


                }
            }



        new JFXPanel();
        ring();

        while(true){

            // SONNERIE
            OffsetDateTime odt = OffsetDateTime.now();
            int h = odt.getHour();
            int m = odt.getMinute();
            if(odt.getSecond() == 0) {
                if (h == 8 && m == 0) {
                    ring();
                } else if (h == 8 && m == 55) {
                    ring();
                } else if (h == 9 && m == 50) {
                    ring();
                } else if (h == 10 && m == 5) {
                    ring();
                } else if (h == 11 && m == 0) {
                    ring();
                } else if (h == 11 && m == 55) {
                    ring();
                } else if (h == 13 && m == 25) {
                    ring();
                } else if (h == 14 && m == 20) {
                    ring();
                }
                if (h == 15 && m == 15) {
                    ring();
                }
                if (h == 15 && m == 30) {
                    ring();
                }
                if (h == 16 && m == 25) {
                    ring();
                }
                if (h == 17 && m == 20) {
                    ring();
                }
            }
            int s = odt.getSecond();
            String hd = (h<10?"0"+h:String.valueOf(h));
            String md = (m<10?"0"+m:String.valueOf(m));
            String sd = (s<10?"0"+s:String.valueOf(s));
            System.out.println("Il est "+hd+"h"+md+"; "+sd+" secondes se sont écoulées.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
    public static void ring(){
        Media hit = new Media(new File(RING_LOCATION).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }


    private static String getFileChecksum(MessageDigest digest, File file) throws IOException
    {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }
    private static void replace(){
        try{
            URL website = new URL(RING_LOCATION_INTERNET);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(RING_LOCATION);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String downloadAndGetMd5(){
        try{
            URL website = new URL(RING_LOCATION_INTERNET);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(RING_LOCATION_BIS);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();

            File distant = new File(RING_LOCATION_BIS);
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            String checksumf = getFileChecksum(md5Digest, distant);
            distant.delete();
            return checksumf;
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "0";
    }
}
