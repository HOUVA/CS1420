package lab12;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A House that can be drawn. It has a position, size, and color.
 * 
 * @author CS 1420 course staff
 * @version November 11, 2025
 */
public class House {
	private int positionX;
	private int positionY;
	private int sizeX;
	private int sizeY;
	private Color color;
	
	/**
	 * Constructs a house with the given position, size, and color.
	 * 
	 * @param positionX - x-coordinate position
	 * @param positionY - y-coordinate position
	 * @param sizeX - value of horizontal size
	 * @param sizeY - value of vertical size
	 * @param color - color value
	 */
	public House(int positionX, int positionY, int sizeX, int sizeY, Color color) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.color = color;
	}
	
	/**
	 * Moves the position to the new coordinates.
	 * 
	 * @param newX - new x-coordinate
	 * @param newY - new y-coordinate
	 */
	public void move(int newX, int newY) {
		this.positionX = newX;
		this.positionY = newY;
	}
	
	/**
	 * Sets the color of the House.
	 * 
	 * @param newColor - new color value
	 */
	public void setColor(Color newColor) {
		this.color = newColor;
	}
	
	/**
	 * Draws the House to the given Graphics context.
	 * 
	 * @param g - graphics context onto which we can draw
	 */
	public void draw(Graphics g) {
		int eve = (int)(10 * this.sizeX / 50);
		int roof = (int)(25 * this.sizeY / 50);
		int doorX = this.positionX + (int)(20 * this.sizeX / 50);
		int doorY = this.positionY + (int)(45 * this.sizeY / 50);
		int doorWidth = (int)(15 * this.sizeX / 50);
		int doorHeight = (int)(30 * this.sizeY / 50);
		
		g.setColor(this.color);
		g.fillRect(this.positionX + eve, this.positionY + roof, this.sizeX, this.sizeY);
		g.setColor(Color.GRAY);
		g.fillPolygon(new int[]{this.positionX, this.positionX + this.sizeX + 2 * eve, this.positionX + eve + this.sizeX / 2}, 
					  new int[]{this.positionY + roof, this.positionY + roof, this.positionY}, 3);
		g.setColor(Color.BLACK);
		g.fillRect(doorX, doorY, doorWidth, doorHeight);
	}
}