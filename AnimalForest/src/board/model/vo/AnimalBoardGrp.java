package board.model.vo;

public class AnimalBoardGrp {

	private int grp1No;
	private String grp1Name;
	private AnimalBoardGrp2 animalGrp2; //소분류 객체
	
	public AnimalBoardGrp2 getAnimalGrp1() {
		return animalGrp2;
	}
	public void setAnimalGrp1(AnimalBoardGrp2 animalGrp2) {
		this.animalGrp2 = animalGrp2;
	}
	public int getGrp1No() {
		return grp1No;
	}
	public void setGrp1No(int grp1No) {
		this.grp1No = grp1No;
	}
	public String getGrp1Name() {
		return grp1Name;
	}
	public void setGrp1Name(String grp1Name) {
		this.grp1Name = grp1Name;
	}
	
	
}
