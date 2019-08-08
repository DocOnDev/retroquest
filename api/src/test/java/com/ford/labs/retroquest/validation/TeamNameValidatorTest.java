/*
 * Copyright © 2018 Ford Motor Company
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ford.labs.retroquest.validation;


import com.ford.labs.retroquest.exception.EmptyTeamNameException;
import com.ford.labs.retroquest.exception.SpecialCharacterTeamNameException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamNameValidatorTest {

    private TeamNameValidator validator = new TeamNameValidator();

    @Test(expected = EmptyTeamNameException.class)
    public void cannotSubmitEmptyTeamName() {
        validator.isValid("", null);
    }

    @Test(expected = EmptyTeamNameException.class)
    public void cannotSubmitNullTeamName() {
        validator.isValid(null, null);
    }

    @Test(expected = SpecialCharacterTeamNameException.class)
    public void cannotSubmitTeamNameWithSpecialCharacters() {
        String teamName = "Ford~!@#$%^&*()+=<>?`.,/|[]{};:'Labs\"";
        validator.isValid(teamName, null);
    }

    @Test
    public void canSubmitValidTeamName() {
        assertThat(validator.isValid("Ford Labs", null)).isTrue();
    }

    @Test
    public void canSubmitValidTeamNameWithNumbers() {
        assertThat(validator.isValid("Ford Labs 2018", null)).isTrue();
    }

    @Test(expected = EmptyTeamNameException.class)
    public void cannotSubmitTeamNameWithOnlyWhitespace() {
        validator.isValid("            ", null);
    }

}
