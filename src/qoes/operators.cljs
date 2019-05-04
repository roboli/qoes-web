(ns qoes.operators)

(def CLARO "CLARO")
(def MOVISTAR "MOVISTAR")
(def TIGO "TIGO")
(def UNITEL "UNITEL")
(def UNKNOWN "UNKNOWN")

(def op-ranges [[TIGO, 3000, 3228],
                [CLARO, 3229, 3229],
                [TIGO, 3230, 3309],
                [MOVISTAR, 3400, 3449],
                [TIGO, 4000, 4099],
                [CLARO, 4100, 4299],
                [MOVISTAR, 4300, 4475],
                [TIGO, 4476, 4699],
                [CLARO, 4700, 4772],
                [TIGO, 4773, 4819],
                [UNITEL, 4820, 4821],
                [TIGO, 4822, 5009],
                [CLARO, 5010, 5019],
                [MOVISTAR, 5020, 5029],
                [TIGO, 5030, 5069],
                [MOVISTAR, 5070, 5109],
                [CLARO, 5110, 5139],
                [MOVISTAR, 5140, 5149],
                [TIGO, 5150, 5199],
                [TIGO, 5200, 5209],
                [MOVISTAR, 5210, 5299],
                [TIGO, 5300, 5309],
                [CLARO, 5310, 5311],
                [MOVISTAR, 5312, 5313],
                [TIGO, 5314, 5389],
                [MOVISTAR, 5390, 5409],
                [CLARO, 5410, 5499],
                [MOVISTAR, 5500, 5509],
                [CLARO, 5510, 5517],
                [MOVISTAR, 5518, 5519],
                [TIGO, 5521, 5529],
                [CLARO, 5531, 5539],
                [MOVISTAR, 5540, 5542],
                [CLARO, 5543, 5544],
                [MOVISTAR, 5545, 5549],
                [TIGO, 5550, 5553],
                [CLARO, 5554, 5579],
                [TIGO, 5580, 5581],
                [CLARO, 5582, 5599],
                [MOVISTAR, 5600, 5608],
                [CLARO, 5610, 5639],
                [MOVISTAR, 5640, 5689],
                [CLARO, 5690, 5699],
                [TIGO, 5700, 5709],
                [CLARO, 5710, 5718],
                [TIGO, 5719, 5789],
                [MOVISTAR, 5790, 5799],
                [TIGO, 5800, 5809],
                [CLARO, 5810, 5818],
                [TIGO, 5819, 5819],
                [CLARO, 5820, 5879],
                [TIGO, 5880, 5909],
                [CLARO, 5910, 5914],
                [MOVISTAR, 5915, 5917],
                [TIGO, 5918, 5919],
                [CLARO, 5920, 5989],
                [TIGO, 5990, 5999]])

(defn identify-op [phone]
  (or
   (->> op-ranges
        (some #(when (and (<= (second %) phone) (<= phone (last %))) %))
        (first))
   UNKNOWN))
