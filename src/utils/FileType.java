package utils;

import java.util.Arrays;
import java.util.Collection;


/**
 * This page:
 * <a href="http://fileinfo.com/filetypes/common">http://fileinfo.com/filetypes/common</a>
 * in enum form.
 * 
 * Groups FileExtension by general file type
 * 
 * @see FileExtension
 * @author George Bernard
 */
public enum FileType {
    TEXT(FileExtension.DOC,
         FileExtension.DOCX,
         FileExtension.TEX,
         FileExtension.LOG,
         FileExtension.MSG,
         FileExtension.ODT,
         FileExtension.PAGES,
         FileExtension.RTF,
         FileExtension.TXT,
         FileExtension.WPD,
         FileExtension.WPS),
    DATA(FileExtension.CSV,
         FileExtension.DAT,
         FileExtension.GED,
         FileExtension.KEY,
         FileExtension.KEYCHAIN,
         FileExtension.PPS,
         FileExtension.PPT,
         FileExtension.PPTX,
         FileExtension.SDF,
         FileExtension.TAR,
         FileExtension.VCF,
         //Added XML File to DATA grouping, think it was forgotten
         FileExtension.XML),
    AUDIO(FileExtension.AIF,
          FileExtension.IFF,
          FileExtension.M3U,
          FileExtension.M4A,
          FileExtension.MID,
          FileExtension.MP3,
          FileExtension.MPA,
          FileExtension.WAV,
          FileExtension.WMA),
    VIDEO(FileExtension.THREE_G2,
          FileExtension.THREE_GP,
          FileExtension.ASF,
          FileExtension.AVI,
          FileExtension.FLV,
          FileExtension.M4V,
          FileExtension.MOV,
          FileExtension.MP4,
          FileExtension.MPG,
          FileExtension.RM,
          FileExtension.SRT,
          FileExtension.SWF,
          FileExtension.VOB,
          FileExtension.WMV),
    THREE_D_IMAGE(FileExtension.THREE_DM,
                  FileExtension.THREE_DS,
                  FileExtension.MAX,
                  FileExtension.OBJ),
    RASTER_IMAGE(FileExtension.BMP,
                 FileExtension.DDS,
                 FileExtension.GIF,
                 FileExtension.JPG,
                 FileExtension.PNG,
                 FileExtension.PSD,
                 FileExtension.PSPIMAGE,
                 FileExtension.TGA,
                 FileExtension.THM,
                 FileExtension.TIF,
                 FileExtension.TIFF,
                 FileExtension.YUV),
    VECTOR_IMAGE(FileExtension.AI,
                 FileExtension.EPS,
                 FileExtension.PS,
                 FileExtension.SVG),
    PAGE_LAYOUT(FileExtension.INDD,
                FileExtension.PCT,
                FileExtension.PDF),
    SPREADSHEET(FileExtension.XLR,
                FileExtension.XLS,
                FileExtension.XLSX),
    DATABASE(FileExtension.ACCDB,
             FileExtension.DB,
             FileExtension.DBF,
             FileExtension.MDB,
             FileExtension.PDB,
             FileExtension.SQL),
    EXECUTABLE(FileExtension.APK,
               FileExtension.APP,
               FileExtension.BAT,
               FileExtension.CGI,
               FileExtension.COM,
               FileExtension.EXE,
               FileExtension.GADGET,
               FileExtension.JAR,
               FileExtension.WSF),
    GAME(FileExtension.DEM,
         FileExtension.GAM,
         FileExtension.NES,
         FileExtension.ROM,
         FileExtension.SAV),
    CAD(FileExtension.DWG,
        FileExtension.DXF),
    GIS(FileExtension.GPX,
        FileExtension.KML,
        FileExtension.KMZ),
    WEB(FileExtension.ASP,
        FileExtension.ASPX,
        FileExtension.CER,
        FileExtension.CFM,
        FileExtension.CSR,
        FileExtension.CSS,
        FileExtension.HTM,
        FileExtension.HTML,
        FileExtension.JS,
        FileExtension.JSP,
        FileExtension.PHP,
        FileExtension.RSS,
        FileExtension.XHTML),
    PLUGIN(FileExtension.CRX,
           FileExtension.PLUGIN),
    FONT(FileExtension.FNT,
         FileExtension.FON,
         FileExtension.OTF,
         FileExtension.TTF),
    SYSTEM(FileExtension.CAB,
           FileExtension.CPL,
           FileExtension.CUR,
           FileExtension.DESKTHEMEPACK,
           FileExtension.DLL,
           FileExtension.DMP,
           FileExtension.DRV,
           FileExtension.ICNS,
           FileExtension.ICO,
           FileExtension.LNK,
           FileExtension.SYS),
    SETTINGS(FileExtension.CFG,
             FileExtension.INI,
             FileExtension.PRF),
    ENCODED(FileExtension.HQX,
            FileExtension.MIM,
            FileExtension.UUE),
    COMPRESSED(FileExtension.SEVEN_Z,
               FileExtension.CBR,
               FileExtension.DEB,
               FileExtension.GZ,
               FileExtension.PKG,
               FileExtension.RAR,
               FileExtension.RPM,
               FileExtension.SITX,
               FileExtension.TAR_GZ,
               FileExtension.ZIP,
               FileExtension.ZIPX),
    DISK_IMAGE(FileExtension.BIN,
               FileExtension.CUE,
               FileExtension.DMG,
               FileExtension.ISO,
               FileExtension.MDF,
               FileExtension.TOAST,
               FileExtension.VCD),
    DEVELOPER(FileExtension.C,
              FileExtension.CLASS,
              FileExtension.CPP,
              FileExtension.CS,
              FileExtension.DTD,
              FileExtension.FLA,
              FileExtension.H,
              FileExtension.JAVA,
              FileExtension.LUA,
              FileExtension.M,
              FileExtension.PL,
              FileExtension.PY,
              FileExtension.SH,
              FileExtension.SLN,
              FileExtension.SWIFT,
              FileExtension.VB,
              FileExtension.VCXPROJ,
              FileExtension.XCODEPROJ),
    BACKUP(FileExtension.BAK,
           FileExtension.TMP),
    MISC(FileExtension.CRDOWNLOAD,
         FileExtension.ICS,
         FileExtension.MSI,
         FileExtension.PART,
         FileExtension.TORRENT);
    
    Collection<FileExtension> myFileExtensions;

    private FileType (FileExtension ... aExtensions) {
        myFileExtensions = Arrays.asList(aExtensions);
    }

    public Collection<FileExtension> getExtensions () {
        return myFileExtensions;
    }
}
