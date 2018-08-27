# image-zoom-view
Imageview zoom library for android. It's simple show image library.
</br>
</br>
<b>Demo</b>
</br>
</br>
 <a href="https://imgflip.com/gif/2falhe"><img src="https://i.imgflip.com/2falhe.gif" title="made at imgflip.com"/></a>
<a href="https://imgflip.com/gif/2g4o4b"><img src="https://i.imgflip.com/2g4o4b.gif" title="made at imgflip.com"/></a>
<a href="https://imgflip.com/gif/2g4o6v"><img src="https://i.imgflip.com/2g4o6v.gif" title="made at imgflip.com"/></a>
<h2>Quick start</h2>
<pr>
<b>1)</b> Add this library as a dependency in your app's build.project file.

<pre>
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  </pre>

<b>2)</b> Add the view to your layout XML.
<pre>implementation 'com.github.hsmnzaydn:imagezoom:1.2.0'</pre>

<b>3)</b> Add the view to your layout XML.
<pre>&lt;com.ozaydin.serkan.com.image_zoom_view.ImageViewZoom
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@drawable/aleyna_fox" /&gt;</pre>
    
<h2>Features</h2>
<pr>
    <ul>
	    <li><b>Drawing as round</b></li>
	    <pre>&lt;com.ozaydin.serkan.com.image_zoom_view.ImageViewZoom
    	     android:layout_width="wrap_content"
             android:layout_height="wrap_content"
	     app:circle="true"
             android:src="@drawable/aleyna_fox" /&gt;</pre>
          <li><b>Get ImageViewZoom's base64 decoded</b></li>
	    <br>
	    Returns ImageViewZoom's base64 
             <pre>
imageViewZoom.getAsBase64();
             </pre>
	    <li><b>Save Image As File</b></li>
	    <b>Simple Usage</b>
	    <pre>
imageViewZoom.saveImage(MainActivity.this, "ImageViewZoom", "test", Bitmap.CompressFormat.JPEG, 1, new SaveFileListener() {
            @Override
            public void onSuccess(File file) {
                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFail(Exception excepti) {
                Toast.makeText(MainActivity.this,"Error Save",Toast.LENGTH_SHORT).show();
            }
        });
	    </pre>
	    <li><b>Show Option TO User</b> </li>
	    <pre>
	       ImageViewZoomConfig imageViewZoomConfig=new ImageViewZoomConfig();
               imageViewZoomConfig.saveProperty(true); // for show save opion to user
               imageViewZoom.setConfig(imageViewZoomConfig);
	    </pre>
	<h2> Dependicies </h2>
	<pr>
		<a href="https://github.com/davemorrissey/subsampling-scale-image-view"><b>Subsampling Scale Image View</b></a>
	    
