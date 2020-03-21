package cn;

import org.junit.Test;

public class FileTest {

    @Test
    public void testFile() {
        Integer a = 'j'+'h'+'i'+'a'+'d'+'d'+'a'+'b'+'b'+'b'+'b'+'b'+'b'+'c'+'c'+'b'+'p'+'c'+'c'+'b'+'b'+'e'+'b'+'e'+'b'+'f'+'c'+'c'+'b'+'c'+'c'+'b'+'c'+'b'+'k'+'b'+'c'+'c'+'b'+'b'+'c'+'d'+'d'+'h'+'f'+'c'+'e'+'b'+'l'+'e'+'l'+'j'+'g'+'i'+'c'+'a'+'b'+'b'+'a'+'e'+'c'-61*'a'+61;
        System.out.println(a);
        a+='a'+'a'+'c'+'c'+'b'+'h'+'a'+'d'+'e'+'e'+'d'+'a'+'e'+'d'+'a'+'c'+'a'+'a'+'c'+'c'+'a'+'c'+'d'+'d'+'d'+'e'+'a'+'b'+'a'+'c'+'b'+'a'+'b'+'a'+'a'+'i'+'i'+'a'+'c'+'d'+'e'+'d'+'d'+'d'+'a'+'a'-46*'a'+46;
        System.out.println(a);
        Integer b = 'n'+'h'+'h'+'g'+'j'+'i'+'j'+'j'+'i'+'f'+'k'+'k'+'k'+'j'+'a'+'e'+'d'+'e'+'d'+'g'+'a'+'a'+'a'+'a'+'a'+'a'+'h'+'c'+'c'+'d'+'d'+'d'+'f'+'f'+'c'+'l'+'e'-37*'a'+37;
        System.out.println(b);
        b+='i'+'b'+'b'+'n'+'d'+'d'+'c'+'a'+'d'+'c'+'a'+'d'+'c'+'a'+'a'+'c'+'c'+'a'+'c'+'c'+'a'+'c'+'c'+'a'+'c'+'c'+'e'+'c'-28*'a'+28;
        System.out.println(b);
        Integer c = 'b'+'f'+'g'+'i'+'b'+'c'+'j'+'a'+'e'+'g'+'k'+'f'+'b'+'b'+'l'+'a'+'e'-17*'a'+17;
        System.out.println(c);
        c+=6;
        System.out.println(c);
Integer d = 'b'+'c'+'b'+'a'+'b'+'a'+'c'+'b'+'a'+'b'+'b'+'e'+'n'+'f'+'c'+'c'+'a'+'e'+'e'+'b'+'a'+'b'+'c'+'b'+'a'+'b'-26*'a'+26;
Integer e = 'a'+'b'+'f'+'d'+'d'+'j'+'b'+'d'+'d'+'d'+'d'+'b'+'d'+'d'+'a'+'c'+'f'+'b'+'c'+'a'+'f'+'b'+'a'+'a'+'a'+'g'+'a'+'a'+'a'+'a'+'g'+'a'+'a'+'a'+'a'+'a'+'a'+'a'+'a'+'b'+'a'+'b'+'b'+'a'+'a'+'a'+'a'+'b'+'a'+'b'+'b'+'a'+'a'-53*'a'+53;
        System.out.println(d);
        System.out.println(e);
        System.out.println(d + e);
    }

}
