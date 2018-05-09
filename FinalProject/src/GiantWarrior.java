
public class GiantWarrior extends Troop {
	public GiantWarrior(float x, float y, boolean enemy) {
		super(x, y, 150, 30, 1, 0, 7, enemy, V.GIANTWARRIOR_ICON, V.GIANTWARRIOR_ATTACK_ICON);
	}

	public void upgrade() {
		super.upgrade(15, 5);
	}

	public String toString() {
		return "Giant Warrior";
	}
}