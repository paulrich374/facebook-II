package NineChapter;
/*
 * Base64: 
 * 	It's basically a way of encoding arbitrary "binary data" in "ASCII text". 
 * 	It takes 4 characters(6Bit算一個Character) "per 3 bytes of data"(每三個Bytes), plus potentially a bit of padding at the end.
	Essentially each 6 bits of the input is encoded in a 64-character alphabet. The "standard" alphabet uses A-Z, a-z, 0-9 and + and /, with = as a padding character. 
	There are URL-safe variants.
	E.g., 01001000 01100101 01101100 3 bytes
		  010010 00 0110 0101 01  101100 3 bytes
	      010010 000110  010101   101100 4 characters
	      XX010010 XX00010 XX010101 XX101100 padding to a byte
	        18d       6d      21d      45d
	        'S'       'G'     'V'      't' 
	
	http://gemsres.com/story/mar06/192527/mccaffrey-fig3.gif
 * WHy: just like sprites, you save HTTP requests. Solve Sprites's drawbacks
 * How: Data URIs allow you to embed images directly into your CSS files
 * 
Sprites's drawbacks
1.hard to maintain and update
2.increased memory consumption, sprites with a lot of whitespace
3.bleedthrough other elements
 Approach# Sprite
.mysprite {
    background: url(mysprite.png) no-repeat;
}

.icon1 {
    background-position: 16px 16px;
}

.icon2 {
    background-position: 32px 16px;
}
 Approach# Base64
.mysprite {
    // no longer needed 
}
.icon1 {
    background: url(data:image/png;base64,<data>) no-repeat;
}
.icon2 {
    background: url(data:image/png;base64,<data>) no-repeat;
}
Drawback:
1.roughly 33% larger
2.data URIs aren’t supported on IE6 or IE7
3.may possibly take longer to process than binary data
 * 
 * CSS sprites were a solution to the problem of multiple HTTP requests to download multiple images. 
 * Data URIs allow you to embed images directly into your CSS files, solving the same problem in a much more elegant and maintainable way. 
 * Although we still need CSS sprites for older versions of Internet Explorer, that shouldn’t prevent you from investigating the use of data URIs as a better alternative to CSS sprites. 
 * Once IE6 and IE7 go away for good (some day), there really shouldn’t be the need to use CSS sprites so heavily if at all.
 * 
 * http://www.wikihow.com/Encode-a-String-to-Base64-With-Java
 * http://stackoverflow.com/questions/201479/what-is-base-64-encoding-used-for#tfbml-data%7B%22close_iframe%22%3Atrue%2C%22location_url%22%3A%22http%3A%2F%2Fstackoverflow.com%2Fquestions%2F201479%2Fwhat-is-base-64-encoding-used-for%22%7D
 * http://davidbcalhoun.com/2011/when-to-base64-encode-images-and-when-not-to/
 * 
encoding "Man is distinguished, not only by his reason, but by this singular 
passion from other animals, which is a lust of the mind, that by a 
perseverance of delight in the continued and indefatigable generation of 
knowledge, exceeds the short vehemence of any carnal pleasure."
 
TWFuIGlzIGRpc3Rpbmd1aXNoZWQsIG5vdCBvbmx5IGJ5IGhpcyByZWFzb24sIGJ1dCBieSB0aGlz 
IHNpbmd1bGFyIHBhc3Npb24gZnJvbSBvdGhlciBhbmltYWxzLCB3aGljaCBpcyBhIGx1c3Qgb2Yg 
dGhlIG1pbmQsIHRoYXQgYnkgYSBwZXJzZXZlcmFuY2Ugb2YgZGVsaWdodCBpbiB0aGUgY29udGlu 
dWVkIGFuZCBpbmRlZmF0aWdhYmxlIGdlbmVyYXRpb24gb2Yga25vd2xlZGdlLCBleGNlZWRzIHRo 
ZSBzaG9ydCB2ZWhlbWVuY2Ugb2YgYW55IGNhcm5hbCBwbGVhc3VyZS4=
 * 
 * */
public class Base64 {
	private static final String base64code = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz" + "0123456789" + "+/";
 
    private static final int splitLinesAt = 76;
 
    public static byte[] zeroPad(int length, byte[] bytes) {
        byte[] padded = new byte[length]; // initialized to zero by JVM
        System.arraycopy(bytes, 0, padded, 0, bytes.length);
        return padded;
    }
 
    public static String encode(String string) {
 
        String encoded = "";
        byte[] stringArray;
        try {
            stringArray = string.getBytes("UTF-8");  // use appropriate encoding string!
        } catch (Exception ignored) {
            stringArray = string.getBytes();  // use locale default rather than croak
        }
        // determine how many padding bytes to add to the output
        int paddingCount = (3 - (stringArray.length % 3)) % 3;
        // add any necessary padding to the input
        stringArray = zeroPad(stringArray.length + paddingCount, stringArray);
        // process 3 bytes at a time, churning out 4 output bytes
        // worry about CRLF insertions later
        for (int i = 0; i < stringArray.length; i += 3) {
            int j = ((stringArray[i] & 0xff) << 16) +
                ((stringArray[i + 1] & 0xff) << 8) + 
                (stringArray[i + 2] & 0xff);
            encoded = encoded + base64code.charAt((j >> 18) & 0x3f) +
                base64code.charAt((j >> 12) & 0x3f) +
                base64code.charAt((j >> 6) & 0x3f) +
                base64code.charAt(j & 0x3f);
        }
        // replace encoded padding nulls with "="
        return splitLines(encoded.substring(0, encoded.length() -
            paddingCount) + "==".substring(0, paddingCount));
 
    }
    /*
     * Lastly, we package the output, after padding it, 
     * by inserting CRLFs at the required 76-byte boundaries, 
     * using a separate subroutine for clarity.

     * */
    public static String splitLines(String string) {
 
        String lines = "";
        for (int i = 0; i < string.length(); i += splitLinesAt) {
 
            lines += string.substring(i, Math.min(string.length(), i + splitLinesAt));
            lines += "\r\n";
 
        }
        return lines;
 
    }
    public static void main(String[] args) {
    	String str = "Man is distinguished,  not only by his reason";
    	String[] strs = str.split("\\W");
        for (int i = 0; i < strs.length; i++) {
            //System.err.println("encoding \"" + strs[i] + "\"");
            //System.out.println(encode(strs[i]));
        }
        System.out.println(encode(str));
 
    }
}
