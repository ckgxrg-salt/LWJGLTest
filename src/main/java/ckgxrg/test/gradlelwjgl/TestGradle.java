package ckgxrg.test.gradlelwjgl;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import static org.lwjgl.opengl.GL40.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.btssstudio.util.bitk.BITK;
import com.btssstudio.util.bitk.BITKString;
import com.btssstudio.util.gl.ShaderUtil;
import com.btssstudio.util.gl.ShapeUtil;

public class TestGradle {
	private static long window;
	private static int width = 800;
	private static int height = 600;
	public static void init() {
		BITK.setBITKLangDirectoryPath("lang");
		BITK.useCustomLangFile("ck_bt");
		BITK.init();
		if(!glfwInit()) {
			throw new IllegalStateException("*GLFW, Stay Determined!");
		}
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
	    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
		window = glfwCreateWindow(width, height, new BITKString("window.title").get(), NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("*Window, Stay Determined!");
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glfwSwapInterval(1);
		glfwShowWindow(window);
	}
	public static void tick() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}
	public static void render() {
		glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT);
		int progID = ShaderUtil.genProgram("shader/general.vert", "shader/general.frag");
		glUseProgram(progID);
		ShapeUtil.drawRectangleTexture2D(-1.0f, -1.0f, 1.8f, 1.8f, "texture/test.png");
	}
	public void run() {
		init();
		while(!glfwWindowShouldClose(window)) {
			tick();
			render();
		}
		glfwDestroyWindow(window);
		glfwTerminate();
	}
    public static void main(String[] args) {
        new TestGradle().run();
    }
}
