package com.ramcosta.composedestinations.manualcomposablecalls

import com.ramcosta.composedestinations.spec.DestinationSpec

class ManualComposableCalls internal constructor(
    private val map: Map<DestinationSpec<*>, DestinationLambda<*>>
) {

    operator fun get(destinationSpec: DestinationSpec<*>): DestinationLambda<*>? {
        return map[destinationSpec]
    }
}