package com.kirby.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kirby.main.Kirby;

public class World {
	
	private Tile[] tiles;
	public static int WIDTH,HEIGHT;
	BufferedImage background;
	
	public World(String path, String path2){
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			background = ImageIO.read(getClass().getResource(path2));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0,0,map.getWidth(),map.getHeight(),pixels,0,map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++) {
				for(int yy = 0; yy < map.getWidth(); yy++) {
					int pixelAtual = pixels[xx + (yy * WIDTH)];
					tiles[xx + (yy * WIDTH)] = new Tile(xx*32, yy*32, Tile.TITLE_TRANSPARENT);
					if(pixelAtual == 0xFF4CFF00){
						tiles[xx + (yy * WIDTH)] = new Floor_Tile(xx*32, yy*32, Tile.TITLE_FLOOR);
					}else if(pixelAtual == 0xFFFF00DC){
						Kirby.player.setX(xx*32);
						Kirby.player.setY(yy*32);
					}
				}
				}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g){
		g.drawImage(background, 0, 0, Kirby.WIDTH, Kirby.HEIGHT, null);
		for(int xx = 0; xx < WIDTH; xx++) {
			for(int yy = 0; yy < HEIGHT; yy++) {
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
				}
			}
		}

}
