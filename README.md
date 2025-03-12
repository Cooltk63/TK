package com.tcs.jasper;

import java.util.HashMap;
import net.sf.jasperreports.functions.annotations.Function;
import net.sf.jasperreports.functions.annotations.FunctionCategories;
import net.sf.jasperreports.functions.annotations.FunctionParameter;
import net.sf.jasperreports.functions.annotations.FunctionParameters;

@FunctionCategories({SeriesCategory.class})
public class SeriesFunction {
   @Function("Roman_series")
   @FunctionParameters({@FunctionParameter("series"), @FunctionParameter("a_r")})
   public static String Roman_series(Integer series, Character a_r) {
      String roman_serial = null;
      HashMap<Integer, String> roman_map = new HashMap();
      roman_map.put(1, "I");
      roman_map.put(2, "II");
      roman_map.put(3, "III");
      roman_map.put(4, "IV");
      roman_map.put(5, "V");
      roman_map.put(6, "VI");
      roman_map.put(7, "VII");
      roman_map.put(8, "VIII");
      roman_map.put(9, "IX");
      roman_map.put(10, "X");
      roman_map.put(11, "XI");
      roman_map.put(12, "XII");
      roman_map.put(13, "XIII");
      roman_map.put(14, "XIV");
      roman_map.put(15, "XV");
      roman_map.put(16, "XVI");
      roman_map.put(17, "XVII");
      roman_map.put(18, "XVIII");
      roman_map.put(19, "XIX");
      roman_map.put(20, "XX");
      roman_map.put(21, "XXI");
      roman_map.put(22, "XXII");
      roman_map.put(23, "XXIII");
      roman_map.put(24, "XXIV");
      roman_map.put(25, "XXV");
      roman_map.put(26, "XXVI");
      roman_map.put(27, "XXVII");
      roman_map.put(28, "XXVIII");
      roman_map.put(29, "XXIX");
      roman_map.put(30, "XXX");
      roman_map.put(31, "XXXI");
      roman_map.put(32, "XXXII");
      roman_map.put(33, "XXXIII");
      roman_map.put(34, "XXXIV");
      roman_map.put(35, "XXXV");
      roman_map.put(36, "XXXVI");
      roman_map.put(37, "XXXVII");
      roman_map.put(38, "XXXVIII");
      roman_map.put(39, "XXXIX");
      roman_map.put(40, "XL");
      roman_map.put(41, "XLI");
      roman_map.put(42, "XLII");
      roman_map.put(43, "XLIII");
      roman_map.put(44, "XLIV");
      roman_map.put(45, "XLV");
      roman_map.put(46, "XLVI");
      roman_map.put(47, "XLVII");
      roman_map.put(48, "XLVIII");
      roman_map.put(49, "XLIX");
      roman_map.put(50, "L");
      roman_map.put(51, "LI");
      roman_map.put(52, "LII");
      roman_map.put(53, "LIII");
      roman_map.put(54, "LIV");
      roman_map.put(55, "LV");
      roman_map.put(56, "LVI");
      roman_map.put(57, "LVII");
      roman_map.put(58, "LVIII");
      roman_map.put(59, "LIX");
      roman_map.put(60, "LX");
      roman_map.put(61, "LXI");
      roman_map.put(62, "LXII");
      roman_map.put(63, "LXIII");
      roman_map.put(64, "LXIV");
      roman_map.put(65, "LXV");
      roman_map.put(66, "LXVI");
      roman_map.put(67, "LXVII");
      roman_map.put(68, "LXVIII");
      roman_map.put(69, "LXIX");
      roman_map.put(70, "LXX");
      roman_map.put(71, "LXXI");
      roman_map.put(72, "LXXII");
      roman_map.put(73, "LXXIII");
      roman_map.put(74, "LXXIV");
      roman_map.put(75, "LXXV");
      roman_map.put(76, "LXXVI");
      roman_map.put(77, "LXXVII");
      roman_map.put(78, "LXXVIII");
      roman_map.put(79, "LXXIX");
      roman_map.put(80, "LXXX");
      roman_map.put(81, "LXXXI");
      roman_map.put(82, "LXXXII");
      roman_map.put(83, "LXXXIII");
      roman_map.put(84, "LXXXIV");
      roman_map.put(85, "LXXXV");
      roman_map.put(86, "LXXXVI");
      roman_map.put(87, "LXXXVII");
      roman_map.put(88, "LXXXVIII");
      roman_map.put(89, "LXXXIX");
      roman_map.put(90, "XC");
      roman_map.put(91, "XCI");
      roman_map.put(92, "XCII");
      roman_map.put(93, "XCIII");
      roman_map.put(94, "XCIV");
      roman_map.put(95, "XCV");
      roman_map.put(96, "XCVI");
      roman_map.put(97, "XCVII");
      roman_map.put(98, "XCVIII");
      roman_map.put(99, "XCIX");
      roman_map.put(100, "C");
      HashMap<Integer, String> alpha_map = new HashMap();
      alpha_map.put(1, "A");
      alpha_map.put(2, "B");
      alpha_map.put(3, "C");
      alpha_map.put(4, "D");
      alpha_map.put(5, "E");
      alpha_map.put(6, "F");
      alpha_map.put(7, "G");
      alpha_map.put(8, "H");
      alpha_map.put(9, "I");
      alpha_map.put(10, "J");
      alpha_map.put(11, "K");
      alpha_map.put(12, "L");
      alpha_map.put(13, "M");
      alpha_map.put(14, "N");
      alpha_map.put(15, "O");
      alpha_map.put(16, "P");
      alpha_map.put(17, "Q");
      alpha_map.put(18, "R");
      alpha_map.put(19, "S");
      alpha_map.put(20, "T");
      alpha_map.put(21, "U");
      alpha_map.put(22, "V");
      alpha_map.put(23, "W");
      alpha_map.put(24, "X");
      alpha_map.put(25, "Y");
      alpha_map.put(26, "Z");
      if (a_r.compareTo('R') == 0) {
         try {
            roman_serial = (String)roman_map.get(series);
         } catch (Exception var7) {
            roman_serial = "#NA";
         }
      } else if (a_r.compareTo('A') == 0) {
         try {
            roman_serial = (String)alpha_map.get(series);
         } catch (Exception var6) {
            roman_serial = "#NA";
         }
      }

      return roman_serial;
   }
}
