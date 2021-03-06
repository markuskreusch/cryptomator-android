package org.cryptomator.presentation.ui.bottomsheet

import android.os.Bundle
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.dialog_bottom_sheet_vault_settings.*
import org.cryptomator.generator.BottomSheet
import org.cryptomator.presentation.R
import org.cryptomator.presentation.model.VaultModel

@BottomSheet(R.layout.dialog_bottom_sheet_vault_settings)
class SettingsVaultBottomSheet : BaseBottomSheet<SettingsVaultBottomSheet.Callback>() {

	interface Callback {
		fun onDeleteVaultClick(vaultModel: VaultModel)
		fun onRenameVaultClick(vaultModel: VaultModel)
		fun onLockVaultClick(vaultModel: VaultModel)
		fun onChangePasswordClick(vaultModel: VaultModel)
	}

	override fun setupView() {
		val vaultModel = requireArguments().getSerializable(VAULT_ARG) as VaultModel

		if (vaultModel.isLocked) {
			lock_vault.visibility = LinearLayout.GONE
		}
		val cloudType = vaultModel.cloudType
		cloud_image.setImageResource(cloudType.cloudImageResource)
		vault_name.text = vaultModel.name
		vault_path.text = vaultModel.path

		et_rename.setOnClickListener {
			callback?.onRenameVaultClick(vaultModel)
			dismiss()
		}
		delete_vault.setOnClickListener {
			callback?.onDeleteVaultClick(vaultModel)
			dismiss()
		}
		lock_vault.setOnClickListener {
			callback?.onLockVaultClick(vaultModel)
			dismiss()
		}
		change_password.setOnClickListener {
			callback?.onChangePasswordClick(vaultModel)
			dismiss()
		}
	}

	companion object {
		private const val VAULT_ARG = "vault"
		fun newInstance(vaultModel: VaultModel): SettingsVaultBottomSheet {
			val dialog = SettingsVaultBottomSheet()
			val args = Bundle()
			args.putSerializable(VAULT_ARG, vaultModel)
			dialog.arguments = args
			return dialog
		}
	}
}
