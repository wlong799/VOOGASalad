package game_object.block;

import java.util.ArrayList;

import game_object.Dimension;
import game_object.Position;

public class AbstractBlock implements IBlock {

	Position myPos;
	ArrayList<String> myImgPaths;
	Dimension myDimension;
	
	@Override
	public void setPosition(Position pos) {
		myPos = pos;
	}

	@Override
	public Position getPosition() {
		return myPos;
	}

	@Override
	public ArrayList<String> getImagePaths() {
		return myImgPaths;
	}

	@Override
	public void setImagePaths(ArrayList<String> imgPaths) {
		myImgPaths = imgPaths;
	}

	@Override
	public void setDimension(Dimension d) {
		myDimension = d;
	}

	@Override
	public Dimension getDimension() {
		return myDimension;
	}

}
