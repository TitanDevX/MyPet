/*
 * This file is part of mypet-api
 *
 * Copyright (C) 2011-2016 Keyle
 * mypet-api is licensed under the GNU Lesser General Public License.
 *
 * mypet-api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mypet-api is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package de.Keyle.MyPet.api.skill.skills;

import de.Keyle.MyPet.api.skill.SkillInfo;
import de.Keyle.MyPet.api.skill.SkillName;
import de.Keyle.MyPet.api.skill.SkillProperties;
import de.Keyle.MyPet.api.skill.SkillProperties.NBTdatatypes;
import de.Keyle.MyPet.api.skill.skilltree.SkillTreeSkill;

@SkillName(value = "Pickup", translationNode = "Name.Skill.Pickup")
@SkillProperties(
        parameterNames = {"range", "addset_range", "exp_pickup"},
        parameterTypes = {NBTdatatypes.Double, NBTdatatypes.String, NBTdatatypes.Boolean},
        parameterDefaultValues = {"1.0", "add", "false"})
public class PickupInfo extends SkillTreeSkill implements SkillInfo {
    protected double range = 0;
    protected boolean expPickup = false;

    public PickupInfo(boolean addedByInheritance) {
        super(addedByInheritance);
    }

    public SkillInfo cloneSkill() {
        PickupInfo newSkill = new PickupInfo(this.isAddedByInheritance());
        newSkill.setProperties(getProperties());
        return newSkill;
    }
}