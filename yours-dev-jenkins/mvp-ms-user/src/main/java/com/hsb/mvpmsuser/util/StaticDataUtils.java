package com.hsb.mvpmsuser.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.hsb.mvpmsuser.model.domain.CountriesAndRegions;

public class StaticDataUtils {
	
	public static List<CountriesAndRegions> initCountriesAndRegionsList = 
			Arrays.asList(
				new CountriesAndRegions(1, "Angola", "安哥拉", "安哥拉", "AO", 244, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(2, "Afghanistan", "阿富汗", "阿富汗", "AF", 93, 0F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(3, "Albania", "阿尔巴尼亚", "阿爾巴尼亞", "AL", 355, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(4, "Algeria", "阿尔及利亚", "阿爾及利亞", "DZ", 213, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(5, "Andorra", "安道尔共和国", "安道爾共和國", "AD", 378, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(6, "Anguilla", "安圭拉岛", "安圭拉島", "AI", 1264, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(7, "Antigua and Barbuda", "安提瓜和巴布达", "安提瓜和巴布達", "AG", 1268, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(8, "Argentina", "阿根廷", "阿根廷", "AR", 54, -11F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(9, "Armenia", "亚美尼亚", "亞美尼亞", "AM", 374, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(10, "Ascension", "阿森松", "阿森松", "", 247, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(11, "Australia", "澳大利亚", "澳大利亞", "AU", 61, 2F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(12, "Austria", "奥地利", "奧地利", "AT", 43, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(13, "Azerbaijan", "阿塞拜疆", "阿塞拜疆", "AZ", 994, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(14, "Bahamas", "巴哈马", "巴哈馬", "BS", 1242, -13F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(15, "Bahrain", "巴林", "巴林", "BH", 973, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(16, "Bangladesh", "孟加拉国", "孟加拉國", "BD", 880, -2F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(17, "Barbados", "巴巴多斯", "巴巴多斯", "BB", 1246, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(18, "Belarus", "白俄罗斯", "白俄羅斯", "BY", 375, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(19, "Belgium", "比利时", "比利時", "BE", 32, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(20, "Belize", "伯利兹", "伯利兹", "BZ", 501, -14F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(21, "Benin", "贝宁", "貝寧", "BJ", 229, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(22, "Bermuda Is.", "百慕大群岛", "百慕大群島", "BM", 1441, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(23, "Bolivia", "玻利维亚", "玻利維亞", "BO", 591, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(24, "Botswana", "博茨瓦纳", "博茨瓦納", "BW", 267, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(25, "Brazil", "巴西", "巴西", "BR", 55, -11F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(26, "Brunei", "文莱", "文萊", "BN", 673, 0F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(27, "Bulgaria", "保加利亚", "保加利亞", "BG", 359, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(28, "Burkina-faso", "布基纳法索", "布基納法索", "BF", 226, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(29, "Burma", "缅甸", "緬甸", "MM", 95, -1.3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(30, "Burundi", "布隆迪", "布隆迪", "BI", 257, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(31, "Cameroon", "喀麦隆", "喀麥隆", "CM", 237, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(32, "Canada", "加拿大", "加拿大", "CA", 1, -13F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(33, "Cayman Is.", "开曼群岛", "開曼群島", "", 1345, -13F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(34, "Central African Republic", "中非共和国", "中非共和國", "CF", 236, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(35, "Chad", "乍得", "乍得", "AO", 244, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(36, "Chile", "智利", "智利", "CL", 56, -13F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(37, "China", "中国", "中國", "CN", 86, 0F, true, false, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(38, "Colombia", "哥伦比亚", "哥倫比亞", "CO", 57, 0F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(39, "Congo", "刚果", "剛果", "CG", 242, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(40, "Cook Is.", "库克群岛", "庫克群島", "CK", 682, -18.3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(41, "Costa Rica", "哥斯达黎加", "哥斯達黎加", "CR", 506, -14F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(42, "Cuba", "古巴", "古巴", "CU", 53, -13F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(43, "Cyprus", "塞浦路斯", "塞浦路斯", "CY", 357, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(44, "Czech Republic", "捷克", "捷克", "CZ", 420, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(45, "Denmark", "丹麦", "丹麥", "DK", 45, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(46, "Djibouti", "吉布提", "吉布提", "DJ", 253, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(47, "Dominica Rep.", "多米尼加共和国", "多米尼加共和國", "DO", 1890, -13F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(48, "Ecuador", "厄瓜多尔", "厄瓜多爾", "EC", 593, -13F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(49, "Egypt", "埃及", "埃及", "EG", 20, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(50, "EI Salvador", "萨尔瓦多", "薩爾瓦多", "SV", 503, -14F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(51, "Estonia", "爱沙尼亚", "愛沙尼亞", "EE", 372, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(52, "Ethiopia", "埃塞俄比亚", "埃塞俄比亞", "ET", 251, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(53, "Fiji", "斐济", "斐濟", "FJ", 679, 4F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(54, "Finland", "芬兰", "芬蘭", "FI", 358, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(55, "France", "法国", "法國", "FR", 33, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(56, "French Guiana", "法属圭亚那", "法屬圭亞那", "GF", 594, 12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(57, "Gabon", "加蓬", "加蓬", "GA", 241, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(58, "Gambia", "冈比亚", "岡比亞", "GM", 220, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(59, "Georgia", "格鲁吉亚", "格魯吉亞", "GE", 995, 0F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(60, "Germany", "德国", "德國", "DE", 49, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(61, "Ghana", "加纳", "加納", "GH", 233, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(62, "Gibraltar", "直布罗陀", "直布羅陀", "GI", 350, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(63, "Greece", "希腊", "希臘", "GR", 30, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(64, "Grenada", "格林纳达", "格林納達", "AO", 1809, -14F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(65, "Guam", "关岛", "關島", "GU", 1671, 2F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(66, "Guatemala", "危地马拉", "危地馬拉", "GT", 502, -14F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(67, "Guinea", "几内亚", "幾内亞", "GN", 224, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(68, "Guyana", "圭亚那", "圭亞那", "GY", 592, -11F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(69, "Haiti", "海地", "海地", "HT", 509, -13F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(70, "Honduras", "洪都拉斯", "洪都拉斯", "HN", 504, -14F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(71, "Hongkong", "香港", "安哥拉", "HK", 852, 0F, true, false, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(72, "Hungary", "匈牙利", "匈牙利", "HU", 36, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(73, "Iceland", "冰岛", "冰島", "IS", 354, -9F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(74, "India", "印度", "印度", "IN", 91, -2.3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(75, "Indonesia", "印度尼西亚", "印度尼西亞", "ID", 62, -0.3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(76, "Iran", "伊朗", "伊朗", "IR", 98, -4.3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(77, "Iraq", "伊拉克", "伊拉克", "IQ", 964, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(78, "Ireland", "爱尔兰", "愛爾蘭", "IE", 353, -4.3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(79, "Israel", "以色列", "以色列", "IL", 972, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(80, "Italy", "意大利", "意大利", "IT", 39, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(81, "Ivory Coast", "科特迪瓦", "科特迪瓦", "", 225, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(82, "Jamaica", "牙买加", "牙買加", "JM", 1876, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(83, "Japan", "日本", "日本", "JP", 81, 1F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(84, "Jordan", "约旦", "約旦", "JO", 962, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(85, "Kampuchea", "柬埔寨", "柬埔寨", "KH", 855, -1F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(86, "Kazakstan", "哈萨克斯坦", "哈薩克斯坦", "KZ", 327, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(87, "Kenya", "肯尼亚", "肯尼亞", "KE", 254, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(88, "Korea", "韩国", "韓國", "KR", 82, 1F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(89, "Kuwait", "科威特", "科威特", "KW", 965, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(90, "Kyrgyzstan", "吉尔吉斯坦", "吉爾吉斯斯坦", "KG", 331, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(91, "Laos", "老挝", "老撾", "LA", 856, -1F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(92, "Latvia", "拉脱维亚", "拉脫維亞", "LV", 371, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(93, "Lebanon", "黎巴嫩", "黎巴嫩", "LB", 961, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(94, "Lesotho", "莱索托", "萊索托", "LS", 266, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(95, "Liberia", "利比里亚", "利比里亞", "LR", 231, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(96, "Libya", "利比亚", "利比亞", "LY", 218, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(97, "Liechtenstein", "列支敦士登", "列支敦士登", "LI", 423, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(98, "Lithuania", "立陶宛", "立陶宛", "LT", 370, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(99, "Luxembourg", "卢森堡", "盧森堡", "LU", 352, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(100, "Macao", "澳门", "澳門", "MO", 853, 0F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(101, "Madagascar", "马达加斯加", "馬達加斯加", "MG", 261, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(102, "Malawi", "马拉维", "馬拉維", "MW", 265, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(103, "Malaysia", "马来西亚", "馬來西亞", "MY", 60, -0.5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(104, "Maldives", "马尔代夫", "馬爾代夫", "MV", 960, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(105, "Mali", "马里", "馬里", "ML", 223, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(106, "Malta", "马耳他", "馬耳他", "MT", 356, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(107, "Mariana Is.", "马里亚那群岛", "馬里亞納群島", "", 1670, 1F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(108, "Martinique", "马提尼克", "馬提尼克", "", 596, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(109, "Mauritius", "毛里求斯", "毛里求斯", "MU", 230, -4F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(110, "Mexico", "墨西哥", "墨西哥", "MX", 52, -15F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(111, "Moldova", "摩尔多瓦", "摩爾多瓦", "MD", 373, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(112, "Monaco", "摩纳哥", "摩納哥", "MC", 377, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(113, "Mongolia", "蒙古", "蒙古", "MN", 976, 0F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(114, "Montserrat Is.", "蒙特塞拉特岛", "蒙特塞拉特島", "MS", 1664, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(115, "Morocco", "摩洛哥", "摩洛哥", "MA", 212, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(116, "Mozambique", "莫桑比克", "莫桑比克", "MZ", 258, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(117, "Namibia", "纳米比亚", "納米比亞", "NA", 264, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(118, "Nauru", "瑙鲁", "瑙魯", "NR", 674, 4F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(119, "Nepal", "尼泊尔", "尼泊爾", "NP", 977, -2.3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(120, "Netheriands Antilles", "荷属安的列斯", "荷屬安的列斯", "", 599, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(121, "Netherlands", "荷兰", "荷蘭", "NL", 31, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(122, "New Zealand", "新西兰", "新西蘭", "NZ", 64, 4F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(123, "Nicaragua", "尼加拉瓜", "尼加拉瓜", "NI", 505, -14F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(124, "Niger", "尼日尔", "尼日爾", "NE", 227, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(125, "Nigeria", "尼日利亚", "尼日利亞", "NG", 234, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(126, "North Korea", "朝鲜", "朝鮮", "KP", 850, 1F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(127, "Norway", "挪威", "挪威", "NO", 47, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(128, "Oman", "阿曼", "阿曼", "OM", 968, -4F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(129, "Pakistan", "巴基斯坦", "巴基斯坦", "PK", 92, -2.3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(130, "Panama", "巴拿马", "巴拿馬", "PA", 507, -13F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(131, "Papua New Cuinea", "巴布亚新几内亚", "巴布亞新幾内亞", "PG", 675, 2F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(132, "Paraguay", "巴拉圭", "巴拉圭", "PY", 595, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(133, "Peru", "秘鲁", "秘魯", "PE", 51, -13F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(134, "Philippines", "菲律宾", "菲律賓", "PH", 63, 0F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(135, "Poland", "波兰", "波蘭", "PL", 48, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(136, "French Polynesia", "法属玻利尼西亚", "法屬波利尼西亞", "PF", 689, 3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(137, "Portugal", "葡萄牙", "葡萄牙", "PT", 351, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(138, "Puerto Rico", "波多黎各", "波多黎各", "PR", 1787, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(139, "Qatar", "卡塔尔", "卡塔爾", "QA", 974, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(140, "Reunion", "留尼旺", "留尼旺", "", 262, -4F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(141, "Romania", "罗马尼亚", "羅馬尼亞", "RO", 40, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(142, "Russia", "俄罗斯", "俄羅斯", "RU", 7, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(143, "Saint Lueia", "圣卢西亚", "聖盧西亞", "LC", 1758, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(144, "Saint Vincent", "圣文森特岛", "聖文森特島", "VC", 1784, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(145, "Samoa Eastern", "东萨摩亚(美)", "東薩摩亞(美)", "", 684, -19F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(146, "Samoa Western", "西萨摩亚", "西薩摩亞", "", 685, -19F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(147, "San Marino", "圣马力诺", "聖馬力諾", "SM", 378, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(148, "Sao Tome and Principe", "圣多美和普林西比", "聖多美和普林西比", "ST", 239, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(149, "Saudi Arabia", "沙特阿拉伯", "沙特阿拉伯", "SA", 966, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(150, "Senegal", "塞内加尔", "塞内加爾", "SN", 221, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(151, "Seychelles", "塞舌尔", "塞舌爾", "SC", 248, -4F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(152, "Sierra Leone", "塞拉利昂", "塞拉利昂", "SL", 232, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(153, "Singapore", "新加坡", "新加坡", "SG", 65, 0.3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(154, "Slovakia", "斯洛伐克", "斯洛伐克", "SK", 421, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(155, "Slovenia", "斯洛文尼亚", "斯洛文尼亞", "SI", 386, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(156, "Solomon Is.", "所罗门群岛", "所羅門群島", "SB", 677, 3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(157, "Somali", "索马里", "索馬里", "SO", 252, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(158, "South Africa", "南非", "南非", "ZA", 27, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(159, "Spain", "西班牙", "西班牙", "ES", 34, 34F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(160, "Sri Lanka", "斯里兰卡", "斯里蘭卡", "LK", 94, 0F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(161, "Sudan", "苏丹", "蘇丹", "SD", 249, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(162, "Suriname", "苏里南", "蘇里南", "SR", 597, -11.3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(163, "Swaziland", "斯威士兰", "斯威士蘭", "SZ", 268, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(164, "Sweden", "瑞典", "瑞典", "SE", 46, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(165, "Switzerland", "瑞士", "瑞士", "CH", 41, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(166, "Syria", "叙利亚", "敘利亞", "SY", 963, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(167, "Taiwan", "台湾省", "台灣省", "TW", 886, 0F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(168, "Tajikstan", "塔吉克斯坦", "塔吉克斯坦", "TJ", 992, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(169, "Tanzania", "坦桑尼亚", "坦桑尼亞", "TZ", 255, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(170, "Thailand", "泰国", "泰國", "TH", 66, -1F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(171, "Togo", "多哥", "多哥", "TG", 228, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(172, "Tonga", "汤加", "湯加", "TO", 676, 4F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(173, "Trinidad and Tobago", "特立尼达和多巴哥", "特立尼達和多巴哥", "TT", 1809, -12F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(174, "Tunisia", "突尼斯", "突尼斯", "TN", 216, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(175, "Turkey", "土耳其", "土耳其", "TR", 90, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(176, "Turkmenistan", "土库曼斯坦", "土庫曼斯坦", "TM", 993, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(177, "Uganda", "乌干达", "烏干達", "UG", 256, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(178, "Ukraine", "乌克兰", "烏克蘭", "UA", 380, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(179, "United Arab Emirates", "阿拉伯联合酋长国", "阿拉伯聯合酋長國", "AE", 971, -4F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(180, "United Kiongdom", "英国", "英國", "GB", 44, -8F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(181, "United States of America", "美国", "美國", "US", 1, -13F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(182, "Uruguay", "乌拉圭", "烏拉圭", "UY", 598, -10.3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(183, "Uzbekistan", "乌兹别克斯坦", "烏兹別克斯坦", "UZ", 233, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(184, "Venezuela", "委内瑞拉", "委内瑞拉", "VE", 58, -12.3F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(185, "Vietnam", "越南", "越南", "VN", 84, -1F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(186, "Yemen", "也门", "也門", "YE", 967, -5F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(187, "Yugoslavia", "南斯拉夫", "南斯拉夫", "YU", 381, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(188, "Zimbabwe", "津巴布韦", "津巴布韋", "ZW", 263, -6F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(189, "Zaire", "扎伊尔", "扎伊爾", "ZR", 243, -7F, true, true, LocalDateTime.now(), LocalDateTime.now()),
				new CountriesAndRegions(190, "Zambia", "贊比亞", "贊比亞", "ZM", 260, -6F, true, true, LocalDateTime.now(), LocalDateTime.now())
			); 
	
	private StaticDataUtils() {}
	
}
