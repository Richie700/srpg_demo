package cn.ching.srpg_demo.game.map;

import android.graphics.Path;
import android.graphics.Rect;

public class Hexagon implements Geometry {

	protected int x, y, halfWidth, midHeight, endHeight;

	public Hexagon() {

	}

	public Hexagon(int x, int y, int halfWidth, int midHeight, int endHeight) {
		this.x = x;
		this.y = y;
		this.halfWidth = halfWidth;
		this.midHeight = midHeight;
		this.endHeight = endHeight;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHalfWidth() {
		return halfWidth;
	}

	public int getWidth() {
		return halfWidth + halfWidth;
	}

	public int getMidHeight() {
		return midHeight;
	}

	public int getEndHeight() {
		return endHeight;
	}

	public int getBaseHeight() {
		return endHeight + midHeight;
	}

	public int getHeight() {
		return endHeight + midHeight + midHeight;
	}

	private Rect frameRect = null;
	@Override
	public Rect getFrameRect() {
		if(frameRect == null) {
			frameRect = new Rect(x, y, x + halfWidth + halfWidth, y + endHeight + midHeight + endHeight);
		}
		return frameRect;
	}

	private int[] center = null;
	@Override
	public int[] getCenter() {
		if(center == null) {
			center = new int[] {x + halfWidth, y + endHeight + (midHeight >> 1)};
		}
		return center;
	}

	private int[][] endpoints = null;
	@Override
	public int[][] getEndpoints() {
		if(endpoints == null) {
			int x1 = x + halfWidth;
			int x2 = x + halfWidth + halfWidth;
			int y1 = y + endHeight;
			int y2 = y + endHeight + midHeight;
			int y3 = y + endHeight + midHeight + endHeight;
			endpoints = new int[][] {
				new int[] {x1, y},
				new int[] {x2, y1},
				new int[] {x2, y2},
				new int[] {x1, y3},
				new int[] {x, y2},
				new int[] {x, y1}
			};
		}
		return endpoints;
	}

	private Path path = null;
	@Override
	public Path getPath() {
		if(path == null) {
			path = new Path();
			getPath(path);
		}
		return path;
	}

	@Override
	public Path getPath(Path path) {
		int[][] endpoints = getEndpoints();
		path.moveTo(endpoints[0][0], endpoints[0][1]);
		path.lineTo(endpoints[1][0], endpoints[1][1]);
		path.lineTo(endpoints[2][0], endpoints[2][1]);
		path.lineTo(endpoints[3][0], endpoints[3][1]);
		path.lineTo(endpoints[4][0], endpoints[4][1]);
		path.lineTo(endpoints[5][0], endpoints[5][1]);
		path.close();
		return path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endHeight;
		result = prime * result + halfWidth;
		result = prime * result + midHeight;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Hexagon other = (Hexagon)obj;
		if(endHeight != other.endHeight)
			return false;
		if(halfWidth != other.halfWidth)
			return false;
		if(midHeight != other.midHeight)
			return false;
		if(x != other.x)
			return false;
		if(y != other.y)
			return false;
		return true;
	}

}
