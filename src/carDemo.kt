open class Car(
    val brand : String,
    val model : String,
    val year : Int,
    var color : String
){
    var currentSpeed: Int = 0
        private set

    fun accelerate(amount:Int){
        if(amount<=0){
            println("acceleration must be positive")
            return
        }
        val newSpeed =  currentSpeed + amount
        val maxSpeed = 220
        if(maxSpeed>newSpeed){
            currentSpeed = maxSpeed
            println("Max speed reached! Current speed: $currentSpeed km/h")
        } else {
            currentSpeed = maxSpeed
            println("Accelerating... Current speed: $currentSpeed km/h")
        }
    }
    fun brake(amount:Int){
        if(amount<=0){
            println("brake amount must be positive")
            return
        }
        val newSpeed = currentSpeed - amount
        if(newSpeed<0){
            currentSpeed = 0
            println("Car stopped. Current speed: $currentSpeed km/h")
        } else {
            currentSpeed = newSpeed
            println("Braking... Current speed: $currentSpeed km/h")
        }
    }
    open fun displayInfo() {
        println("\n--- Car Information ---")
        println("Brand: $brand")
        println("Model: $model")
        println("Year: $year")
        println("Color: $color")
        println("Current Speed: $currentSpeed km/h")
    }
    init {
        println("New car created: $brand $model ($year), Color: $color ,Initial speed: $currentSpeed km/h")
    }
}

class ElectricCar(
    brand: String,
    model: String,
    year: Int,
    color: String,
    val batteryRange : Int
):Car(brand,model,year,color) {
    init{
        println("Electric Car specifics: Battery Range: $batteryRange km")
    }
    fun chargeBattery(){
        println("Charging the $brand $model's battery...")
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Battery Range: $batteryRange km")
        println("-----------------------")
    }
}

fun main(){
    val car1 = Car("Toyota","Corolla",2021,"Silver")
    val car2 = Car(brand = "BMW", model = "X5", year = 2023, color = "Black")

    println("\n--- Car 1 Info (from outside, accessing properties) ---")
    println("Brand: ${car1.brand}")
    println("Model: ${car1.model}")
    println("Year: ${car1.year}")
    println("Color: ${car1.color}")
    println("Current Speed: ${car1.currentSpeed}")

    car1.color = "Red"
    println("Car1 new color: ${car1.color}")

    println("\n--- Testing Car 1 ---")
    car1.displayInfo()
    car1.accelerate(50)
    car1.accelerate(30)
    car1.brake(20)
    car1.displayInfo()
    car1.accelerate(200)
    car1.brake(300)
    println("\n--- Testing Car 2 ---")
    car2.displayInfo()
    car2.accelerate(80)
    car2.color = "Matte black"
    car2.displayInfo()

    println("\n\n--- Testing Electric Car ---")
    val eCar1 = ElectricCar("Tesla", "Model S", 2023, "White", 500)

    println("Electric Car Brand: ${eCar1.brand}")
    eCar1.accelerate(80)
    eCar1.displayInfo()

    println("Battery Range ${eCar1.batteryRange} km")
    eCar1.chargeBattery()
}