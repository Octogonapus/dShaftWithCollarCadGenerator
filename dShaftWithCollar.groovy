import eu.mihosoft.vrl.v3d.parametrics.*;

CSG generate(){
	String type= "dShaftWithCollar"
	
	if(args==null)
		args=["5mm"]
	
	// The variable that stores the current size of this vitamin
	StringParameter size = new StringParameter(	type+" Default",args.get(0),Vitamins.listVitaminSizes(type))
	HashMap<String,Object> measurments = Vitamins.getConfiguration( type,args[0])

	def collarLengthValue = measurments.collarLength
	def shaftDSectionDiameterValue = measurments.shaftDSectionDiameter
	def priceValue = measurments.price
	def massCentroidYValue = measurments.massCentroidY
	def massCentroidXValue = measurments.massCentroidX
	def collarDiameterValue = measurments.collarDiameter
	def sourceValue = measurments.source
	def massCentroidZValue = measurments.massCentroidZ
	def massKgValue = measurments.massKg
	def lengthValue = measurments.length
	def shaftDiameterValue = measurments.shaftDiameter
	println "Measurment collarLengthValue =  "+collarLengthValue
	println "Measurment shaftDSectionDiameterValue =  "+shaftDSectionDiameterValue
	println "Measurment priceValue =  "+priceValue
	println "Measurment massCentroidYValue =  "+massCentroidYValue
	println "Measurment massCentroidXValue =  "+massCentroidXValue
	println "Measurment collarDiameterValue =  "+collarDiameterValue
	println "Measurment sourceValue =  "+sourceValue
	println "Measurment massCentroidZValue =  "+massCentroidZValue
	println "Measurment massKgValue =  "+massKgValue
	println "Measurment lengthValue =  "+lengthValue
	println "Measurment shaftDiameterValue =  "+shaftDiameterValue

	LengthParameter collarOffset = new LengthParameter("collar offset", 0, [0, lengthValue - collarLengthValue])
	
	CSG shaft = Vitamins.get("dShaft", args[0])
	CSG collar = new Cylinder(collarDiameterValue, collarDiameterValue, collarLengthValue, 30).toCSG().movez(collarOffset.getMM())
	CSG assembly = shaft.union(collar)
	return assembly
		.setParameter(size)
		.setParameter(collarOffset)
		.setRegenerate({generate()})
}

return generate()
