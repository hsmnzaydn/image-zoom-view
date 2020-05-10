
# image-zoom-view  
Imageview zoom library for android. It's simple show image library.  
</br>  
</br>  
<b>Demo</b>  
</br>  
<a href="https://imgflip.com/gif/2falhe"><img src="https://i.imgflip.com/2falhe.gif" title="made at imgflip.com"/></a>  <a href="https://imgflip.com/gif/2g4o4b"><img src="https://i.imgflip.com/2g4o4b.gif" title="made at imgflip.com"/></a>  <a href="https://imgflip.com/gif/2g4o6v"><img src="https://i.imgflip.com/2g4o6v.gif" title="made at imgflip.com"/></a>  

<h2>Quick start</h2>  
<pr>  
<b>1)</b> Add this library as a dependency in your app's build.project file.  
  
<pre>  
allprojects {  
      repositories {  
         maven { url 'https://jitpack.io' }  
      }  
   }  
  </pre>  
  
<b>2)</b> Add this dependency to your app's build.module file.  
<pre>implementation 'com.github.hsmnzaydn:imagezoom:1.3.0'</pre>  
  
<b>3)</b> Add the view to your layout XML.  
<pre>&lt;ozaydin.serkan.com.image_zoom_view.ImageViewZoom  
    android:layout_width="wrap_content"  
    android:layout_height="wrap_content"  
    android:src="@drawable/aleyna_fox" /&gt;</pre>  
      
<h1>Features</h1>  
<pr>  
      <h3>Drawing as round</h3>
       <pre>&lt;ozaydin.serkan.com.image_zoom_view.ImageViewZoom  
            android:layout_width="wrap_content"  
             android:layout_height="wrap_content"  
             app:circle="true"  
             android:src="@drawable/aleyna_fox" /&gt;</pre> 
              
   <h3>Get ImageViewZoom's base64 decoded</h3>
       Returns ImageViewZoom's base64   
   
    imageViewZoom.getBase64();  

          
       
<h3>Save Image As File</h3>
       

 **Step 1.** </br>
 <b>Init ImageViewZoomConfig</b> </br>
 Set saveProperty field as "true"
 </br>
 </br>
 <b>Init saveMethod </b></br>
 If you want show save option to user only when open dialog you have to set "ImageViewZoomConfig.ImageViewZoomConfigSaveMethod" as "onlyOnDialog" </br>
 If you want save image when run code you have to set "ImageViewZoomConfig.ImageViewZoomConfigSaveMethod" as "always".

     ImageViewZoomConfig imageViewZoomConfig =new ImageViewZoomConfig.Builder().saveProperty(true).saveMethod(ImageViewZoomConfig.ImageViewZoomConfigSaveMethod.onlyOnDialog).build();

**Step 2.** Set Config

    imageViewZoom.setConfig(imageViewZoomConfig);
    
**Step 3.** Use "saveImage()" method

    imageViewZoom.saveImage(MainActivity.this, "ImageViewZoom", "test", Bitmap.CompressFormat.JPEG, 1, imageViewZoomConfig,new SaveFileListener() {  
      @Override   
      public void onSuccess(File file) {  
         Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
       }     
      @Override  
      public void onFail(Exception excepti) {  
        Toast.makeText(MainActivity.this,"Error Save",Toast.LENGTH_SHORT).show();  
      }  
    });
       
         
 <h2> Dependicies </h2> <pr> <a href="https://github.com/davemorrissey/subsampling-scale-image-view"><b>Subsampling Scale Image View</b></a>
