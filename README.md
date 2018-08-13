# image-zoom-view
Imageview zoom library for android. It's simple show image library.
</br>
</br>
<b>Demo</b>
</br>
</br>
 <a href="https://imgflip.com/gif/2falhe"><img src="https://i.imgflip.com/2falhe.gif" title="made at imgflip.com"/></a>

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
<pre>implementation 'com.github.hsmnzaydn:image-zoom-view:1.1.0'</pre>

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
        <pre>
             imageViewZoom.getAsBase64();
        </pre>
        
	</ul>

	<h2> Dependicies </h2>
	<pr>
		<a href="https://github.com/davemorrissey/subsampling-scale-image-view"><b>Subsampling Scale Image View</b></a>

