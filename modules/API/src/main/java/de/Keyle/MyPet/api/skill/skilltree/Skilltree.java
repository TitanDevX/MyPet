/*
 * This file is part of MyPet
 *
 * Copyright © 2011-2018 Keyle
 * MyPet is licensed under the GNU Lesser General Public License.
 *
 * MyPet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MyPet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package de.Keyle.MyPet.api.skill.skilltree;

import de.Keyle.MyPet.api.Configuration;
import de.Keyle.MyPet.api.entity.MyPetType;
import de.Keyle.MyPet.api.skill.Upgrade;
import de.Keyle.MyPet.api.skill.skilltree.levelrule.LevelRule;
import de.keyle.knbt.TagByte;
import de.keyle.knbt.TagCompound;
import de.keyle.knbt.TagList;
import de.keyle.knbt.TagShort;

import java.util.*;

public class Skilltree {

    protected String skillTreeName;
    protected List<String> description = new ArrayList<>();
    protected TagCompound iconItem = null;
    protected String permission = null;
    protected String displayName = null;
    protected int maxLevel = 0;
    protected int requiredLevel = 0;
    protected int order = 0;
    protected Set<MyPetType> mobTypes = new HashSet<>();
    protected Map<LevelRule, Upgrade> upgrades = new HashMap<>();

    public Skilltree(String name) {
        this.skillTreeName = name;
    }

    public String getName() {
        return skillTreeName;
    }

    public List<String> getDescription() {
        return Collections.unmodifiableList(description);
    }

    public void addDescriptionLine(String line) {
        description.add(line);
    }

    public void addDescription(String[] lines) {
        for (String line : lines) {
            addDescriptionLine(line);
        }
    }

    public void removeDescriptionLine(int index) {
        description.remove(index);
    }

    public void clearDescription() {
        description.clear();
    }

    public void setIconItem(TagCompound iconItem) {
        iconItem = new TagCompound(iconItem.getCompoundData());
        this.iconItem = iconItem;
        getIconItem();
    }

    public void setIconItem(short id, short damage, boolean enchantetGlow) {
        getIconItem();

        if (id > 0) {
            iconItem.getCompoundData().put("id", new TagShort(id));
        }
        if (damage >= 0) {
            iconItem.getCompoundData().put("Damage", new TagShort(damage));
        }
        if (!iconItem.getCompoundData().containsKey("tag")) {
            iconItem.getCompoundData().put("tag", new TagCompound());
        }
        TagCompound tagCompound = iconItem.getAs("tag", TagCompound.class);
        if (enchantetGlow) {
            tagCompound.getCompoundData().put("ench", new TagList());
        } else {
            tagCompound.getCompoundData().remove("ench");
        }
    }

    public TagCompound getIconItem() {
        if (iconItem == null) {
            iconItem = new TagCompound();
        }
        iconItem.getCompoundData().put("Count", new TagByte((byte) 1));
        if (!iconItem.getCompoundData().containsKey("id")) {
            iconItem.getCompoundData().put("id", new TagShort((short) 6));
        }
        if (!iconItem.getCompoundData().containsKey("Damage")) {
            iconItem.getCompoundData().put("Damage", new TagShort((short) 0));
        }
        return iconItem;
    }

    public int getMaxLevel() {
        return maxLevel == 0 ? Configuration.LevelSystem.Experience.LEVEL_CAP : maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel < 0 ? 0 : Math.min(maxLevel, Configuration.LevelSystem.Experience.LEVEL_CAP);
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel < 1 ? 1 : requiredLevel;
    }

    public String getDisplayName() {
        if (displayName == null) {
            return skillTreeName;
        }
        return displayName;
    }

    public boolean hasDisplayName() {
        return displayName != null;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPermission() {
        if (permission == null) {
            return skillTreeName;
        }
        return permission;
    }

    public boolean hasCustomPermissions() {
        return permission != null;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Upgrade> getUpgrades(int level) {
        List<Upgrade> upgrades = new ArrayList<>();
        List<LevelRule> rules = new ArrayList<>(this.upgrades.keySet());
        rules.sort(Comparator.comparingInt(LevelRule::getPriority));
        for (LevelRule rule : rules) {
            if (rule.check(level)) {
                upgrades.add(this.upgrades.get(rule));
            }
        }
        return upgrades;
    }

    public void addUpgrade(LevelRule levelRule, Upgrade upgrade) {
        this.upgrades.put(levelRule, upgrade);
    }

    public Set<MyPetType> getMobTypes() {
        return mobTypes;
    }

    public void setMobTypes(Collection<MyPetType> mobTypes) {
        this.mobTypes.clear();
        this.mobTypes.addAll(mobTypes);
    }

    @Override
    public String toString() {
        return "Skilltree{" +
                "skillTreeName='" + skillTreeName + '\'' +
                ", displayName='" + displayName + '\'' +
                (maxLevel > 0 ? ", maxLevel=" + maxLevel : "") +
                (requiredLevel > 0 ? ", requiredLevel=" + requiredLevel : "") +
                '}';
    }
}