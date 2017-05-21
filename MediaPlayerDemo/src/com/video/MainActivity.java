package com.video;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
//����ܲ���ֵ�ý��
public class MainActivity extends Activity implements OnClickListener, OnErrorListener, OnCompletionListener {

	// ������Ƶ����
	private VideoView sv_video;

	private String path = "http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8";
	//��Ӱ��ַ��http://data.vod.itc.cn/?new=/134/254/8qoaCXUIovFdt1gLOlJitB.mp4&vid=1001883384&plat=17&mkey=59G0RDCRldAqtj2IDoAIrrwF3k1Iq6Zb&ch=tv&prod=app
	//m3u8��ַ��http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8
	
	// ��¼��ͣ���ŵ�λ��
	private int currentPosition = 0;

	// ����һ���طŵļ�¼λ��
	private int backPosition = 0;
	private MediaController mMediaController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ȥ��title */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/* ������Ļ���� *//* flag����� �� */
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_main);

		sv_video = (VideoView) findViewById(R.id.sv_video);

		// Create media controller��������Կ�����Ƶ�Ĳ��ţ���ͣ���ظ���seek�Ȳ���������Ҫ��ʵ��
		mMediaController = new MediaController(this);
		sv_video.setMediaController(mMediaController);
		
		/* ����URI �� ָ������ */
		sv_video.setVideoURI(Uri.parse(path));
//		sv_video.requestFocus();  
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);
		Button button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(this);
		Button button11 = (Button) findViewById(R.id.button11);
		button11.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			if (sv_video.isPlaying()) {

			} else {
				sv_video.start();
				if (currentPosition >= 0) {
					sv_video.seekTo(currentPosition);
					currentPosition = -1;
				}
			}
			break;
		case R.id.button2:
			if (sv_video.canPause()) {
				sv_video.resume();
			} else {
				sv_video.pause();
			}
			break;
		case R.id.button3:
			currentPosition = sv_video.getCurrentPosition();
			sv_video.stopPlayback();
			break;
		case R.id.button11:
			startActivity(new Intent(this, VideoPlayerActivity.class));
			break;
		default:
			break;
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		finish();
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		return false;
	}

}
