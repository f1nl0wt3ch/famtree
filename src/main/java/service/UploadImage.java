package service;

import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
public class UploadImage {
     Map config = ObjectUtils.asMap(
    		 "cloud_name", "dtwardllr",
    		 "api_key", "286112722865844",
    		 "api_secret", "iyMAtVTic0ofJ4mCPGMvoBSE5EU"
    		 );
     
     Cloudinary cloudinary = new Cloudinary(config);
}
