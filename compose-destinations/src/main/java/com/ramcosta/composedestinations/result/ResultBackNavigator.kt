package com.ramcosta.composedestinations.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.ramcosta.composedestinations.spec.DestinationSpec

/**
 * Navigator that allows navigating back while passing
 * a result of type [R].
 *
 * If declared as a parameter of a `@Destination` annotated Composable,
 * Compose Destinations will provide a correct implementation. If you're
 * manually calling that Composable, then you can use
 * [com.ramcosta.composedestinations.manualcomposablecalls.resultBackNavigator]
 * extension function to get a correctly typed implementation.
 *
 * Type safety related limitations (compile time enforced):
 * - [R] must be one of String, Boolean, Float, Int, Long, Serializable, or Parcelable.
 * They can be nullable.
 * - [R] type cannot have type arguments itself (f.e you can't use Array<String> even though it is Serializable)
 * - Each annotated Composable can have at most one parameter of type [ResultBackNavigator]
 *
 * @see [com.ramcosta.composedestinations.result.ResultRecipient]
 */
interface ResultBackNavigator<R> {

    /**
     * Goes back to previous destination sending [result].
     *
     * It uses [NavController.navigateUp] internally to go back.
     *
     * Check [com.ramcosta.composedestinations.result.ResultRecipient] to see
     * how to get the result.
     */
    fun navigateBack(result: R)
}

/**
 * Returns a well typed [ResultBackNavigator] for [navController] and [destinationSpec].
 * You shouldn't have to call this directly, it is public to be used in the generated code.
 *
 * If you're manually calling your Composable, then use
 * [com.ramcosta.composedestinations.manualcomposablecalls.resultBackNavigator] instead.
 */
@Composable
inline fun <reified R> resultBackNavigator(
    navController: NavController,
    destinationSpec: DestinationSpec<*>
): ResultBackNavigator<R> = remember {
    ResultBackNavigatorImpl(
        navController = navController,
        resultOriginType = destinationSpec.javaClass,
        resultType = R::class.java
    )
}
