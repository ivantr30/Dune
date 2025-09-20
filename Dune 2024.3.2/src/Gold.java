
public class Gold extends Block {

	public Gold() {
		super(GOLD);
		value = (int) (Math.random()*80)+20;
		passable = true;
		breakable = true;
		picId = 11;
	}
	@Override
	public int takeResourse(int value) {
		if(value <=0) {
			return 0;
		}
		int value2 = Math.min(value, this.value);
		this.value -= value2;
			return value2;
	}
}
