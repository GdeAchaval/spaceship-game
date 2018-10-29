package edu.austral.starship.model.weapon;


import edu.austral.starship.model.bullet.Bullet;
import edu.austral.starship.model.spaceship.Spaceship;

public abstract class WeaponDecorator extends Weapon {
    public WeaponDecorator(Spaceship spaceship) {
        super(spaceship);
    }

    public abstract Bullet shoot();
    private Weapon weapon;

    public Weapon getWeapon() {
        return weapon;
    }
}
