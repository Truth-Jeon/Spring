package org.galapagos.security;

import static org.junit.Assert.*;

import org.galapagos.config.RootConfig;
import org.galapagos.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {
		RootConfig.class,
		SecurityConfig.class
})
public class AvatarTest {
	public static final String AVATAR_UPLOAD_DIR = "/Users/jeonhayoon/upload/avatar";

	@Test
	public void testUpload() throws IOException {
		String username = "unknown";
		
		File src = new File("/Users/jeonhayoon/temp/unknown_user_icon.png");
		File dest = new File(AVATAR_UPLOAD_DIR, username + ".png");
		
		Thumbnails.of(src)
		.size(50, 50)
		.toFile(dest);
	}

}
