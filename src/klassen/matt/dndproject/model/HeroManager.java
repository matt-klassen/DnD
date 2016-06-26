package klassen.matt.dndproject.model;

import klassen.matt.dndproject.model.creature.Hero;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Represents a collection of Heroes
 */
public class HeroManager implements Iterable<Hero> {

    private static HeroManager instance;
    private List<Hero> heroes;

    private HeroManager() {
        heroes = new ArrayList<>();
    }

    public static HeroManager getInstance() {
        if (instance == null) {
            instance = new HeroManager();
        }
        return instance;
    }

    public void addHero(Hero hero) {
        heroes.add(hero);
    }

    public void removeMonster(Hero hero) {
        heroes.remove(hero);
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public boolean hasHero(Hero hero) {
        return heroes.contains(hero);
    }

    public boolean hasAllMonsters(Set<Hero> heroSet) {
        return heroes.containsAll(heroSet);
    }

    @Override
    public Iterator<Hero> iterator() {
        return heroes.iterator();
    }

}
