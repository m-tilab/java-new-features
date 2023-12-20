package java9_to_17.instance_of;

import org.junit.jupiter.api.Test;

public class InstanceOfExample {

    @Test
    void instanceOfExample() {

        AbstractFile abstractFile = new MusicFile();
        useInstanceOf(abstractFile);
    }

    private void useInstanceOf(AbstractFile abstractFile) {

        // Traditional
        if (abstractFile instanceof MusicFile) {

            ((MusicFile) abstractFile).playMusic();
        }

        // New Way
        if (abstractFile instanceof MusicFile musicFile) {

            musicFile.playMusic();
        }
    }
}
