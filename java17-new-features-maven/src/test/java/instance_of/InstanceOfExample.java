package instance_of;

import org.junit.jupiter.api.Test;

public class InstanceOfExample {

    @Test
    public void instanceOfExample() {

        AbstractFile abstractFile = new MusicFile();
        useInstanceOf(abstractFile);
    }

    private void useInstanceOf(AbstractFile abstractFile) {

        if (abstractFile instanceof MusicFile) {

            ((MusicFile) abstractFile).playMusic();
        }

        if (abstractFile instanceof MusicFile musicFile) {

            musicFile.playMusic();
        }
    }
}
