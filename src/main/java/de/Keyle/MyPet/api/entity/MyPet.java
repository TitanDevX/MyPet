/*
 * This file is part of MyPet
 *
 * Copyright (C) 2011-2016 Keyle
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

package de.Keyle.MyPet.api.entity;

import de.Keyle.MyPet.api.player.MyPetPlayer;
import de.Keyle.MyPet.entity.types.MyPetType;
import de.Keyle.MyPet.skill.skilltree.SkillTree;

import java.util.UUID;

public interface MyPet {
    public double getExp();

    public double getHealth();

    public int getHungerValue();

    public MyPetPlayer getOwner();

    public String getPetName();

    public MyPetType getPetType();

    public int getRespawnTime();

    public SkillTree getSkillTree();

    public UUID getUUID();

    public String getWorldGroup();

    public long getLastUsed();
}