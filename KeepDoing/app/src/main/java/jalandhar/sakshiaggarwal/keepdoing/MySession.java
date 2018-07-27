package jalandhar.sakshiaggarwal.keepdoing;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class MySession
{
     private SharedPreferences myprefs;

        public MySession(Context mycontext)
        {
            myprefs= PreferenceManager.getDefaultSharedPreferences(mycontext);
        }

    public void setuname(String uname)
    {
        myprefs.edit().putString("uname",uname).commit();

    }
    public String getuname()
    {
        return myprefs.getString("uname","");
    }
    public void removeuname()
    {
        myprefs.edit().remove("uname");
    }


        public void setname(String name)
        {
            myprefs.edit().putString("name",name).commit();

        }
        public String getname()
        {
            return myprefs.getString("name","");
        }
        public void removename()
        {
            myprefs.edit().remove("name");
        }
    public void setweight(String weight)
    {
        myprefs.edit().putString("weight",weight).commit();

    }
    public String getweight()
    {
        return myprefs.getString("weight","");
    }
    public void removeweight()
    {
        myprefs.edit().remove("weight");
    }
    public void setheight(String height)
    {
        myprefs.edit().putString("height",height).commit();

    }
    public String getheight()
    {
        return myprefs.getString("height","");
    }
    public void removeheight()
    {
        myprefs.edit().remove("height");
    }
    public void setage(String age)
    {
        myprefs.edit().putString("age",age).commit();

    }
    public String getage()
    {
        return myprefs.getString("age","");
    }
    public void removeage()
    {
        myprefs.edit().remove("age");
    }
    public void setgender(String gender)
    {
        myprefs.edit().putString("gender",gender).commit();

    }
    public String getgender()
    {
        return myprefs.getString("gender","");
    }
    public void removegender()
    {
        myprefs.edit().remove("gender");
    }


   /* public void setimage(String image)
    {
        myprefs.edit().putString("image",image).commit();

    }*/


   /* public String getimage()
    {
        return myprefs.getString("image","");
    }
    public void removeimage()
    {
        myprefs.edit().remove("image");
    }*/


   /* public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
        }
        */
}
