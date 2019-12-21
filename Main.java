import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.OffsetDateTime;

public class Main {
    public static final String RING_LOCATION = System.getenv("appdata")+"/autosonnerie/ring.mp3";
    public static void main(String[] args){


        try {
            File f = new File(RING_LOCATION);
            if(!f.exists()){
                URL website = new URL("http://www.website.com/information.asp");
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream(RING_LOCATION);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
}
