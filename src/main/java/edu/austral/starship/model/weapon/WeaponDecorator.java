package edu.austral.starship.model.weapon;


import edu.austral.starship.model.bullet.Bullet;

public abstract class WeaponDecorator {
    public abstract Bullet shoot();
    private Weapon weapon;

    public WeaponDecorator(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
