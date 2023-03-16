package barrera.alejandro.cambiadietas.change_diet.domain.use_case

class ValidateFoodAmount {
    operator fun invoke(foodAmount: String): Result {
        return if (foodAmount.matches(Regex("\\d+(\$|(\\.(\$|\\d+\$)))|"))) {
            Result.Success(foodAmount)
        } else {
            Result.Error
        }

    }

    sealed class Result {
        data class Success(val foodAmount: String): Result()
        object Error: Result()
    }
}