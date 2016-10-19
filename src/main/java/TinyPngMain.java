import com.tinify.Source;
import com.tinify.Tinify;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by batman on 19/10/16.
 */
public class TinyPngMain {

    public static void main(String []args) throws IOException {
        System.out.println("Here I am!");

        String inputDirectory = "/Users/batman/playground/projarea/monster-math-cross-platform/monster-math-2/Resources/Assets";
        String outputDirectory = "/Users/batman/playground/projarea/monster-math-cross-platform/monster-math-2/Resources-opti/Assets";

        //Getting all the files from the input directory.
        final List<File> files = new ArrayList<File>(FileUtils.listFiles(
                new File(inputDirectory),
                new RegexFileFilter(".*\\.png"),
                DirectoryFileFilter.DIRECTORY
        ));
        Tinify.setKey("hz609ThANqbOrnQ1gieKtMNOcPS6kCGN");
        for (int i = 0, j = 0; i < files.size(); i++) {
            File file = files.get(i);
            float fileSizeKB = file.length() / 1024.0f;
            String sourcePath = file.getPath();
            System.out.println("The path: " + sourcePath + " length: " + fileSizeKB);
            if(fileSizeKB >= 100) {
                System.out.println("Ignoring the image since its smaller than min value!");
                continue;
            }
            j++;
            Source source = Tinify.fromFile(sourcePath);
            String destinationPath = sourcePath.replaceAll("Resources", "Resources-opti");
            System.out.println("The destination path: " + destinationPath + " Count: " + j);
            new File(destinationPath.replaceAll("/" + file.getName(), "")).mkdirs();
            source.toFile(destinationPath);
            System.out.println("The File created! at " + destinationPath);
//            break;
        }
    }

}
