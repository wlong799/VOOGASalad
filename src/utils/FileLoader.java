package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * A helpful facade class for the filechooser javafx object.
 * Construct with the nested enum to pick files with the relevant extension.
 * Currently opens up a new stage outside of the current javafx stage
 * <p>
 * Default Starting directory is Java Root folder
 * 
 * @see FileChooser
 * @see FileExtension
 * @author George Bernard
 */
public class FileLoader {
	public static final String RESOURCE_FOLDER = "game_player_resources/GamePlayMenu";
	public static ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_FOLDER);
	private static final String TITLE = "Load File";
    private static final String ERR_MSG = "File Failed To Load";

    /**
     * Starting Directories for FileLoader
     * 
     * @author George Bernard
     */
    public enum StartDirectory {
        /**
         * Platform agnostic user home directory
         */
        HOME_DIRECTORY(System.getProperties().getProperty("user.dir")),
        /**
         * Java Root directory
         */
        SOURCE_DIRECTORY("./"),
    	
    	DEFAULT_DIRECTORY(myResources.getString("DefaultGameDirectory"));
    	
        private String myPath;

        private StartDirectory (String aPath) {
            myPath = aPath;
        }

        /**
         * @return path to directory
         */
        public String getPath () {
            return myPath;
        }
    }

    private FileChooser myFileChooser;
    private static final StartDirectory DEFAULT_START = StartDirectory.SOURCE_DIRECTORY;
    private Collection<FileExtension> myExtensions;

    /**
     * Constructed with no arguments, the loader will simply use all files.
     */
    private FileLoader (StartDirectory aStart) {
        myFileChooser = new FileChooser();
        myFileChooser.setTitle(TITLE);
        myFileChooser.setInitialDirectory(new File(aStart.getPath()));
    }

    private FileLoader () {
        this(DEFAULT_START);
    }

    /**
     * Constructed with the FileTypes to be used as extensions
     * 
     * @see FileExtension
     * @param aFileExtension
     */
    @Deprecated
    public FileLoader (FileExtension ... aFileExtension) {
        this();
        myExtensions = Arrays.asList(aFileExtension);
        myExtensions.forEach(fileType -> myFileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter(fileType.name(), fileType.getExtension())));
    }

    /**
     * If custom file extensions are absolutely required, then use this constructor
     * 
     * @param aCustomExtensions
     */
    public FileLoader (String ... aCustomExtensions) {
        this();
        Arrays.asList(aCustomExtensions).forEach(s -> myFileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter(s, s)));
    }

    /**
     * Constructs a file chooser with given StartDirectory and FileExtension List
     * 
     * @see StartDirectory
     * @see FileExtension
     * 
     * @param aStart
     * @param aFileExtension
     */
    public FileLoader (StartDirectory aStart, FileExtension ... aFileExtension) {
        this(aFileExtension);
    }

    /**
     * Constructs a file chooser with given StartDirectory and FileType
     * 
     * @see StartDirectory
     * @see FileType
     * 
     * @param aStart
     * @param aFileType
     */
    public FileLoader (StartDirectory aStart, FileType aFileType) {
        this(aStart);
        myExtensions = aFileType.myFileExtensions;
        FileChooser.ExtensionFilter filter =
                new FileChooser.ExtensionFilter(aFileType.name(),
                                                aFileType.myFileExtensions.stream()
                                                         .map(f -> {
                                                                  return f.getExtension();
                                                              })
                                                         .collect(Collectors.toList()));

        myFileChooser.getExtensionFilters().add(filter);
    }

    /**
     * Returns the file chosen after the file chooser is completed
     * 
     * @see File
     * @return the file chosen after choosing file
     */
    public File loadSingle () throws FileNotFoundException {
        File loadedFile = myFileChooser.showOpenDialog(new Stage());
        if (loadedFile == null)
            throw new FileNotFoundException(ERR_MSG);
        return loadedFile;
    }

    public List<File> loadMultiple () throws FileNotFoundException {
        List<File> loadedFiles = myFileChooser.showOpenMultipleDialog(new Stage());
        for (File file : loadedFiles)
            if (file == null)
                throw new FileNotFoundException(ERR_MSG);
        return loadedFiles;
    }
    
    //Added this method so user doesn't have choose games in default directory
    public List<File> loadMultipleFromDefaultDirectory() throws FileNotFoundException{
    	File folder = new File(myFileChooser.getInitialDirectory().getAbsolutePath().toString());
		File[] listOfFiles = folder.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.toLowerCase().endsWith(myResources.getString("DefaultExtension"));
		    }
		});
		List<File> allFiles = Arrays.asList(listOfFiles);
		for (File file : allFiles)
            if (file == null)
                throw new FileNotFoundException(ERR_MSG);
        return allFiles;
    }
    
}
