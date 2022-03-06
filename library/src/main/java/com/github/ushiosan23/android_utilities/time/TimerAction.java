package com.github.ushiosan23.android_utilities.time;

/**
 * Actions with "java" make lambda expression support more friendly.
 */
public interface TimerAction<T> {

	/**
	 * Called every timer iteration
	 *
	 * @param info Timer information
	 */
	void invoke(T info);

	/**
	 * Timer action without information
	 */
	interface Empty<T> extends TimerAction<T> {

		/**
		 * Default call information
		 *
		 * @param info Timer information
		 */
		@Override
		default void invoke(T info) {
			invoke();
		}

		/**
		 * Called every timer iteration
		 */
		void invoke();

	}

}
