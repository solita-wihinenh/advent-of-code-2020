package day4

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day4KtTest {

    @Test
    fun countValidPassports() {
        val part1Passports = listOf<Map<String, String>>(
            mapOf<String, String>(
                Pair("ecl", "gry"),
                Pair("pid", "860033327"),
                Pair("eyr", "2020"),
                Pair("hcl", "#fffffd"),
                Pair("byr", "1937"),
                Pair("iyr", "2017"),
                Pair("cid", "147"),
                Pair("hgt", "183cm")
            ),
            mapOf<String, String>(
                Pair("ecl", "amb"),
                Pair("pid", "028048884"),
                Pair("eyr", "2023"),
                Pair("hcl", "#cfa07d"),
                Pair("byr", "1929"),
                Pair("iyr", "2013"),
                Pair("cid", "350")
            ),
            mapOf<String, String>(
                Pair("ecl", "brn"),
                Pair("pid", "760753108"),
                Pair("eyr", "2024"),
                Pair("hcl", "#ae17e1"),
                Pair("byr", "1931"),
                Pair("iyr", "2013"),
                Pair("hgt", "179cm")
            ),
            mapOf<String, String>(
                Pair("ecl", "brn"),
                Pair("pid", "166559648"),
                Pair("eyr", "2025"),
                Pair("hcl", "#cfa07d"),
                Pair("iyr", "2011"),
                Pair("hgt", "59in")
            )
        )

        val part2validPassports = listOf<Map<String, String>>(
            mapOf<String, String>(
                Pair("pid", "087499704"),
                Pair("hgt", "74in"),
                Pair("ecl", "grn"),
                Pair("iyr", "2012"),
                Pair("eyr", "2030"),
                Pair("byr", "1980"),
                Pair("hcl", "#623a2f"),
            ),
            mapOf<String, String>(
                Pair("pid", "896056539"),
                Pair("hgt", "165cm"),
                Pair("ecl", "blu"),
                Pair("iyr", "2014"),
                Pair("eyr", "2029"),
                Pair("byr", "1989"),
                Pair("hcl", "#a97842"),
                Pair("cid", "129"),
            ),
            mapOf<String, String>(
                Pair("pid", "545766238"),
                Pair("hgt", "164cm"),
                Pair("ecl", "hzl"),
                Pair("iyr", "2015"),
                Pair("eyr", "2022"),
                Pair("byr", "2001"),
                Pair("hcl", "#888785"),
                Pair("cid", "88"),
            ),
            mapOf<String, String>(
                Pair("pid", "093154719"),
                Pair("hgt", "158cm"),
                Pair("ecl", "blu"),
                Pair("iyr", "2010"),
                Pair("eyr", "2021"),
                Pair("byr", "1944"),
                Pair("hcl", "#b6652a"),
                Pair("cid", "88"),
            ),
        )

        val part2invalidPassports = listOf<Map<String, String>>(
            mapOf<String, String>(
                Pair("eyr", "1972"),
                Pair("cid", "100"),
                Pair("pid", "186cm"),
                Pair("hgt", "170"),
                Pair("ecl", "amb"),
                Pair("iyr", "2018"),
                Pair("byr", "1926"),
                Pair("hcl", "#18171d"),
            ),
            mapOf<String, String>(
                Pair("eyr", "1967"),
                Pair("pid", "012533040"),
                Pair("hgt", "170cm"),
                Pair("ecl", "grn"),
                Pair("iyr", "2019"),
                Pair("byr", "1946"),
                Pair("hcl", "#602927"),
            ),
            mapOf<String, String>(
                Pair("eyr", "2020"),
                Pair("cid", "277"),
                Pair("pid", "021572410"),
                Pair("hgt", "182cm"),
                Pair("ecl", "brn"),
                Pair("iyr", "2012"),
                Pair("byr", "1992"),
                Pair("hcl", "dab227"),
            ),
            mapOf<String, String>(
                Pair("eyr", "2038"),
                Pair("pid", "3556412378"),
                Pair("hgt", "59cm"),
                Pair("ecl", "zzz"),
                Pair("iyr", "2023"),
                Pair("byr", "2007"),
                Pair("hcl", "74454a"),
            ),
        )
        assertEquals(2, countValidPassports(part1Passports, part1fieldRegexes));
        assertEquals(part2validPassports.count(), countValidPassports(part2validPassports, part2fieldRegexes));
        assertEquals(0, countValidPassports(part2invalidPassports, part2fieldRegexes));
    }
}