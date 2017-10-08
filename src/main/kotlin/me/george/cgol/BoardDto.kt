package me.george.cgol

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class BoardDto {
    @JsonProperty
    var nRows: Int = 0
    @JsonProperty
    var nCols: Int = 0
    /**
     * Rules array must be size 9 (0..8)
     * default is the classic
     */
    @JsonProperty
    var rules: ByteArray = kotlin.ByteArray(9, {i -> if (i == 3 || i == 4 || i == 5) 1 else 0})
    @JsonProperty
    var values: List<String> = Collections.emptyList()
}