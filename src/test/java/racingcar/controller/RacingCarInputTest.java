package racingcar.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class RacingCarInputTest {
    RacingCarInput racingCarInput = new RacingCarInput();

    @ParameterizedTest
    @ValueSource(strings = {" 1, 2 \n2 ", "한글\n3", "Engl,ish\n4"})
    void 입력_테스트(String str) {
        System.setIn(new ByteArrayInputStream(str.getBytes()));
        List<String> readStr = racingCarInput.getUserInput();

        List<String> expectedStr = List.of(str.split("\n")).stream()
                .map(String::trim).toList();
        assertThat(readStr).isEqualTo(expectedStr);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Car1 2", "\n2", "Car1,Car2\n three", "Car1,,Car2\n3,", ",Car1,Car2\n3", "Car1,Car2,\n3"})
    void 잘못된입력_테스트(String str) {
        System.setIn(new ByteArrayInputStream(str.getBytes()));
        assertThatThrownBy(() ->
                racingCarInput.getUserInput()
        ).isInstanceOf(IllegalArgumentException.class);

    }
}