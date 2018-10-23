package edu.austral.starship.model.weapon;
import edu.austral.starship.model.bullet.Bullet;

// change name to rapid fire?
public class BoostedWeapon extends WeaponDecorator {

    public BoostedWeapon(Weapon weapon) {
        super(weapon);
    }

    @Override
    public Bullet shoot() {
        return null;
    }
}
